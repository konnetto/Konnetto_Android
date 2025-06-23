package com.zulfadar.konnetto.ui.screen.friendrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.konnetto.data.model.FriendRequest
import com.zulfadar.konnetto.data.repository.FriendRequestRepository
import com.zulfadar.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FriendRequestViewModel(
    private val friendRequestRepository: FriendRequestRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FriendRequest>>> = MutableStateFlow(
        UiState.Loading)
    val uiState: StateFlow<UiState<List<FriendRequest>>>
        get() = _uiState

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