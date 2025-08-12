package com.konnettoco.konnetto.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.model.Post
import com.konnettoco.konnetto.data.model.SugoiPicks
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.data.repository.SugoiPicksRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PostRepository,
    private val sugoiPicksRepository: SugoiPicksRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sugoiPicksState = MutableStateFlow<UiState<List<SugoiPicks>>>(UiState.Loading)
    val sugoiPicksState = _sugoiPicksState.asStateFlow()

    init {
        getAllPostings()
        getAllSugoiPicks()
    }

    fun getAllPostings() {
        viewModelScope.launch {
            repository.getAllPosts()
                .catch { e -> _uiState.value = UiState.Error(e.message ?: "Unknown Error") }
                .collect { posts -> _uiState.value = UiState.Success(posts) }
        }
    }

    fun getAllSugoiPicks() {
        viewModelScope.launch {
            sugoiPicksRepository.getAllSugoiPicks()
                .catch { e -> _sugoiPicksState.value = UiState.Error(e.message ?: "Unknown Error") }
                .collect { picks -> _sugoiPicksState.value = UiState.Success(picks) }
        }
    }
}
