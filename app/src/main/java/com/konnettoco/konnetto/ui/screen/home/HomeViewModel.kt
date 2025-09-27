package com.konnettoco.konnetto.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.konnettoco.konnetto.data.remote.connection.ApiConfig
import com.konnettoco.konnetto.data.remote.connection.ApiService
import com.konnettoco.konnetto.data.remote.response.DataItem
import com.konnettoco.konnetto.data.remote.response.SugoiPicksDataItem
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class HomeViewModel(
//    private val repository: PostRepository,
//    private val sugoiPicksRepository: SugoiPicksRepository,
): ViewModel() {
    private val apiService: ApiService = ApiConfig.getApiService()

    private val _uiState = MutableStateFlow<UiState<List<DataItem>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sugoiPicksState = MutableStateFlow<UiState<List<SugoiPicksDataItem>>>(UiState.Loading)
    val sugoiPicksState = _sugoiPicksState.asStateFlow()
//    private val _uiState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
//    val uiState = _uiState.asStateFlow()
//
//    private val _sugoiPicksState = MutableStateFlow<UiState<List<SugoiPicks>>>(UiState.Loading)
//    val sugoiPicksState = _sugoiPicksState.asStateFlow()

    init {
        getAllPostings()
        getAllSugoiPicks()
    }

//    fun getAllPostings() {
//        viewModelScope.launch {
//            repository.getAllPosts()
//                .catch { e -> _uiState.value = UiState.Error(e.message ?: "Unknown Error") }
//                .collect { posts -> _uiState.value = UiState.Success(posts) }
//        }
//    }

//    fun getAllSugoiPicks() {
//        viewModelScope.launch {
//            sugoiPicksRepository.getAllSugoiPicks()
//                .catch { e -> _sugoiPicksState.value = UiState.Error(e.message ?: "Unknown Error") }
//                .collect { picks -> _sugoiPicksState.value = UiState.Success(picks) }
//        }
//    }

    fun getAllPostings() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = apiService.getAllPosts()
                Log.d("HomeViewModel", "getAllPostings response: $response")
                val data = response.data?.filterNotNull().orEmpty()
                Log.d("HomeViewModel", "Parsed data size: ${data.size}")
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error in getAllPostings", e)
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

    fun getAllSugoiPicks() {
        viewModelScope.launch {
            _sugoiPicksState.value = UiState.Loading
            try {
                val sugoiPicksResponse = apiService.getallSugoiPicks()
                Log.d("HomeViewModel", "getAllSugoiPicks response: $sugoiPicksResponse")
                val sugoiPicksData = sugoiPicksResponse.data?.filterNotNull().orEmpty()
                Log.d("HomeViewModel", "Parsed sugoi picks size: ${sugoiPicksData.size}")
                _sugoiPicksState.value = UiState.Success(sugoiPicksData)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error in getAllSugoiPicks", e)
                _sugoiPicksState.value = UiState.Error(
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
