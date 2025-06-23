package com.zulfadar.konnetto.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.konnetto.data.model.CurrentlyWatching
import com.zulfadar.konnetto.data.model.Post
import com.zulfadar.konnetto.data.repository.CurrentlyWatchingRepository
import com.zulfadar.konnetto.data.repository.PostRepository
import com.zulfadar.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val postRepository: PostRepository,
    private val currentlyWatchingRepository: CurrentlyWatchingRepository
): ViewModel() {
    private val _uiStatePost: MutableStateFlow<UiState<List<Post>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateCurrentlyWatch: MutableStateFlow<UiState<List<CurrentlyWatching>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Post>>>
        get() = _uiStatePost
    val uiStateCurrentlyWatching: StateFlow<UiState<List<CurrentlyWatching>>>
        get() = _uiStateCurrentlyWatch

    fun getAllPostings() {
        viewModelScope.launch {
            postRepository.getAllPosts()
                .catch {
                    _uiStatePost.value = UiState.Error(it.message.toString())
                }
                .collect { postings ->
                    _uiStatePost.value = UiState.Success(postings)
                }
        }
    }

    fun getAllCurrentlyWatching() {
        viewModelScope.launch {
            currentlyWatchingRepository.getAllCurrentlyWatching()
                .catch {
                    _uiStateCurrentlyWatch.value = UiState.Error(it.message.toString())
                }
                .collect { currentlyWatchingss ->
                    _uiStateCurrentlyWatch.value = UiState.Success(currentlyWatchingss)
                }
        }
    }
}