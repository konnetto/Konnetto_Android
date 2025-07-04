package com.zulfadar.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulfadar.konnetto.data.repository.FriendRequestRepository
import com.zulfadar.konnetto.ui.screen.friendrequest.FriendRequestViewModel

class FriendRequestViewModelFactory(
    private val friendRequestRepository: FriendRequestRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendRequestViewModel::class.java)) {
            return FriendRequestViewModel(friendRequestRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}