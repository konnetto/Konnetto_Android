package com.konnettoco.konnetto.ui.screen.profile.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.model.CurrentlyWatching
import com.konnettoco.konnetto.data.model.Post
import com.konnettoco.konnetto.data.repository.CurrentlyWatchingRepository
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val postRepository: PostRepository,
    private val currentlyWatchingRepository: CurrentlyWatchingRepository
): ViewModel() {
    private val _uiStatePost: MutableStateFlow<UiState<List<Post>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateCurrentlyWatch: MutableStateFlow<UiState<List<CurrentlyWatching>>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiStatePost.asStateFlow()
    val uiStateCurrentlyWatching = _uiStateCurrentlyWatch.asStateFlow()

    init {
        getAllPostings()
        getAllCurrentlyWatching()
    }

    fun getAllPostings() {
        viewModelScope.launch {
            postRepository.getAllPosts()
                .catch {
                    _uiStatePost.value = UiState.Error(it.message.toString())
                }
                .collect { postings ->
                    val currentUserPosts = postings.filter { it.author.isCurrentUser }
                    _uiStatePost.value = UiState.Success(currentUserPosts)
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