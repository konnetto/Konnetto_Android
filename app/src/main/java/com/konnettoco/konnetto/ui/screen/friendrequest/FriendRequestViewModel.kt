package com.konnettoco.konnetto.ui.screen.friendrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.local.model.FriendRequest
import com.konnettoco.konnetto.data.repository.FriendRequestRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FriendRequestViewModel(
    private val friendRequestRepository: FriendRequestRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<FriendRequest>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllFriendRequests()
    }

    fun getAllFriendRequests() {
        viewModelScope.launch {
            friendRequestRepository.getAllFriendRequests()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { friendRequests ->
                    _uiState.value = UiState.Success(friendRequests)
                }
        }
    }
}