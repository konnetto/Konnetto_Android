package com.zulfadar.konnetto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulfadar.konnetto.data.repository.CurrentlyWatchingRepository
import com.zulfadar.konnetto.data.repository.FriendRequestRepository
import com.zulfadar.konnetto.data.repository.NotificationRepository
import com.zulfadar.konnetto.data.repository.PostRepository
import com.zulfadar.konnetto.ui.screen.friendrequest.FriendRequestViewModel
import com.zulfadar.konnetto.ui.screen.home.HomeViewModel
import com.zulfadar.konnetto.ui.screen.notification.NotificationViewModel
import com.zulfadar.konnetto.ui.screen.profile.ProfileViewModel

class ViewModelFactory(
    private val repository: PostRepository,
    private val currentlyWatchingRepository: CurrentlyWatchingRepository,
    private val notificationRepository: NotificationRepository,
    private val friendRequestRepository: FriendRequestRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }  else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                repository,
                currentlyWatchingRepository
            ) as T
        } else if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            return NotificationViewModel(notificationRepository) as T
        } else if (modelClass.isAssignableFrom(FriendRequestViewModel::class.java)) {
            return FriendRequestViewModel(friendRequestRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}