package com.konnettoco.konnetto.ui.screen.profile.friendlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.local.model.FriendList
import com.konnettoco.konnetto.data.repository.FriendListRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FriendListViewModel(
    private val friendListRepository: FriendListRepository
): ViewModel() {
    private val _uiStateFriendList: MutableStateFlow<UiState<List<FriendList>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FriendList>>>
        get() = _uiStateFriendList
    fun getAllFriendList() {
        viewModelScope.launch {
            friendListRepository.getAllFriendList()
                .catch {
                    _uiStateFriendList.value = UiState.Error(it.message.toString())
                }
                .collect { postings ->
                    _uiStateFriendList.value = UiState.Success(postings)
                }
        }
    }
}