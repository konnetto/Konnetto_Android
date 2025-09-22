package com.konnettoco.konnetto.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.model.SugoiPicks
import com.konnettoco.konnetto.data.remote.connection.ApiConfig
import com.konnettoco.konnetto.data.remote.connection.ApiService
import com.konnettoco.konnetto.data.remote.response.DataItem
import com.konnettoco.konnetto.data.repository.SugoiPicksRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
//    private val repository: PostRepository,
    private val sugoiPicksRepository: SugoiPicksRepository,
): ViewModel() {
    private val ordinaryPostApiService: ApiService = ApiConfig.getApiService()

    private val _uiState = MutableStateFlow<UiState<List<DataItem>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()
//    private val _uiState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
//    val uiState = _uiState.asStateFlow()
//
    private val _sugoiPicksState = MutableStateFlow<UiState<List<SugoiPicks>>>(UiState.Loading)
    val sugoiPicksState = _sugoiPicksState.asStateFlow()

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

    fun getAllSugoiPicks() {
        viewModelScope.launch {
            sugoiPicksRepository.getAllSugoiPicks()
                .catch { e -> _sugoiPicksState.value = UiState.Error(e.message ?: "Unknown Error") }
                .collect { picks -> _sugoiPicksState.value = UiState.Success(picks) }
        }
    }

    fun getAllPostings() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = ordinaryPostApiService.getAllPosts()
                val data = response.data?.filterNotNull().orEmpty()
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load data")
            }
        }
    }
}
