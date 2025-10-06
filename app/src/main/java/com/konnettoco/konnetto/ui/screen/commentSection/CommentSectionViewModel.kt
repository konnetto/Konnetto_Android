package com.konnettoco.konnetto.ui.screen.commentSection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.konnettoco.konnetto.data.remote.connection.ApiConfig
import com.konnettoco.konnetto.data.remote.connection.ApiService
import com.konnettoco.konnetto.data.remote.response.CommentsDataItem
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class CommentSectionViewModel(
    private val postId: String
): ViewModel() {
    private val apiService: ApiService = ApiConfig.getApiService()

    private val _uiState = MutableStateFlow<UiState<List<CommentsDataItem>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCommentsByPostId(postId)
    }

    fun getCommentsByPostId(postId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = apiService.getCommentByPostId(postId)
                Log.d("CommentSectionViewModel", "getCommentsByPostId response: $response")
                val data = response.data?.filterNotNull().orEmpty()
                Log.d("CommentSectionViewModel", "Parsed data size: ${data.size}")
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                Log.e("CommentSectionViewModel", "Error in getAllPostings", e)
                _uiState.value = UiState.Error(
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
}