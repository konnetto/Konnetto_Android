package com.konnettoco.konnetto.ui.screen.commentSection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.konnettoco.konnetto.data.remote.ApiConfig
import com.konnettoco.konnetto.data.remote.api.HomeApiService
import com.konnettoco.konnetto.data.remote.dto.CommentRepliesDataItem
import com.konnettoco.konnetto.data.remote.dto.CommentsDataItem
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class CommentSectionViewModel(
    private val postId: String,
): ViewModel() {
    private val apiService: HomeApiService = ApiConfig.getApiService()

    private val _parentCommentUiState = MutableStateFlow<UiState<List<CommentsDataItem>>>(UiState.Loading)
    val parentCommentUiState = _parentCommentUiState.asStateFlow()

    private val _childCommentsMap = MutableStateFlow<Map<String, UiState<List<CommentRepliesDataItem>>>>(emptyMap())
    val childCommentsMap = _childCommentsMap.asStateFlow()

    init {
        getCommentsByPostId()
    }

    fun getCommentsByPostId() {
        viewModelScope.launch {
            _parentCommentUiState.value = UiState.Loading
            try {
                val response = apiService.getCommentByPostId(postId)
                Log.d("CommentSectionViewModel", "getCommentsByPostId response: $response")
                val data = response.data?.filterNotNull().orEmpty()
                Log.d("CommentSectionViewModel", "Parsed data size: ${data.size}")
                _parentCommentUiState.value = UiState.Success(data)
            } catch (e: Exception) {
                Log.e("CommentSectionViewModel", "Error in getCommentsByPostId", e)
                _parentCommentUiState.value = UiState.Error(
                    when (e) {
                        is HttpException -> "Server error: ${e.code()}"
                        is UnknownHostException -> "No internet connection, please check your connection then try again"
                        is JsonSyntaxException -> "Invalid JSON format"
                        else -> "Failed to load data"
                    }
                )
            }
        }
    }

    fun getCommentRepliesByCommentId(commentId: String) {
        viewModelScope.launch {
            _childCommentsMap.value = _childCommentsMap.value.toMutableMap().apply {
                this[commentId] = UiState.Loading
            }

            try {
                val response = apiService.getCommentRepliesByCommentId(postId, commentId)
                Log.d("CommentSectionViewModel", "getCommentsRepliesByCommentId response: $response")
                val replies = response.data?.filterNotNull().orEmpty()
                Log.d("CommentSectionViewModel", "Parsed child comment data size: ${replies.size}")
                _childCommentsMap.value = _childCommentsMap.value.toMutableMap().apply {
                    this[commentId] = UiState.Success(replies)
                }
            } catch (e: Exception) {
                _childCommentsMap.value = _childCommentsMap.value.toMutableMap().apply {
                    this[commentId] = UiState.Error(
//                        e.message ?: "Failed to load replies"
                        when (e) {
                            is HttpException -> "Server error: ${e.code()}"
                            is UnknownHostException -> "No internet connection, please check your connection then try again"
                            is JsonSyntaxException -> "Invalid JSON format"
                            else -> "Failed to load replies"
                        }
                    )
                }
            }
        }
    }

//    fun clearReplies() {
//        _childCommentUiState.update { it - parentCommentId }
//    }
}