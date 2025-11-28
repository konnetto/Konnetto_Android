package com.konnettoco.konnetto.ui.screen.profile.otheruserprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.local.model.Post
import com.konnettoco.konnetto.data.local.model.User
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.data.repository.UserDummyRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OtherUserProfileViewModel(
    private val userRepository: UserDummyRepository,
    private val postRepository: PostRepository,
    private val userId: Long,
): ViewModel() {
    private val _otherUserUiState = MutableStateFlow<UiState<User>>(UiState.Loading)
    val otherUserUiState = _otherUserUiState.asStateFlow()

    private val _otherUserPosts = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val otherUserPosts = _otherUserPosts.asStateFlow()

    init {
        getUserById(userId)
        getUserPosts(userId)
    }

    fun getUserById(id: Long) {
        viewModelScope.launch {
            _otherUserUiState.value = UiState.Loading
            try {
                val user = userRepository.getUserById(id)
                _otherUserUiState.value = UiState.Success(user)
            } catch (e: Exception) {
                _otherUserUiState.value = UiState.Error(e.message ?: "Error")
            }
        }
    }

    fun getUserPosts(userId: Long) {
        viewModelScope.launch {
            _otherUserPosts.value = UiState.Loading
            try {
                postRepository.getPostsByUserId(userId).collect { posts ->
                    _otherUserPosts.value = UiState.Success(posts)
                }
            } catch (e: Exception) {
                _otherUserPosts.value = UiState.Error("Posts not found")
            }
        }
    }
}