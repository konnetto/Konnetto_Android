package com.zulfadar.konnetto.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.konnetto.data.model.Post
import com.zulfadar.konnetto.data.repository.PostRepository
import com.zulfadar.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PostRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Post>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Post>>>
        get() = _uiState

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
}