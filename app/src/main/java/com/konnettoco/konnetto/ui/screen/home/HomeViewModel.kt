package com.konnettoco.konnetto.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.model.Post
import com.konnettoco.konnetto.data.model.SugoiPicks
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.data.repository.SugoiPicksRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PostRepository,
    private val sugoiPicksRepository: SugoiPicksRepository,
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Post>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Post>>>
        get() = _uiState

    private val _SugoiPicksState: MutableStateFlow<UiState<List<SugoiPicks>>> = MutableStateFlow(UiState.Loading)
    val sugoiPicksState: StateFlow<UiState<List<SugoiPicks>>>
        get() = _SugoiPicksState

    fun getAllPostings() {
        viewModelScope.launch {
            repository.getAllPosts()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { posts ->
                    _uiState.value = UiState.Success(posts)
                }
        }
    }

    fun getAllSugoiPicks() {
        viewModelScope.launch {
            sugoiPicksRepository.getAllSugoiPicks()
                .catch {
                    _SugoiPicksState.value = UiState.Error(it.message.toString())
                }
                .collect { sugoiPicks ->
                    _SugoiPicksState.value = UiState.Success(sugoiPicks)
                }
        }
    }
}