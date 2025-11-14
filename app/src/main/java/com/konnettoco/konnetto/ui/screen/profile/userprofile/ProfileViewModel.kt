package com.konnettoco.konnetto.ui.screen.profile.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.local.model.MyLibraryItem
import com.konnettoco.konnetto.data.local.model.Post
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val postRepository: PostRepository,
    private val myLibraryItemRepository: MyLibraryItemRepository
): ViewModel() {
    private val _uiStatePost: MutableStateFlow<UiState<List<Post>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateWatchList: MutableStateFlow<UiState<List<MyLibraryItem>>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiStatePost.asStateFlow()
    val uiStateWatching = _uiStateWatchList.asStateFlow()

    init {
        getAllPostings()
        getAllMyLibrary()
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

    fun getAllMyLibrary() {
        viewModelScope.launch {
            myLibraryItemRepository.getAllMyLibraryItems()
                .catch {
                    _uiStateWatchList.value = UiState.Error(it.message.toString())
                }
                .collect { watchList ->
                    _uiStateWatchList.value = UiState.Success(watchList)
                }
        }
    }
}