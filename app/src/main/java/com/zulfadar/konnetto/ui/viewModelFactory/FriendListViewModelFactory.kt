package com.zulfadar.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulfadar.konnetto.data.repository.FriendListRepository
import com.zulfadar.konnetto.ui.friendlist.FriendListViewModel

class FriendListViewModelFactory(
    private val friendListRepository: FriendListRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendListViewModel::class.java)) {
            return FriendListViewModel(friendListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}