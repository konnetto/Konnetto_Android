package com.zulfadar.konnetto.di

import com.zulfadar.konnetto.data.repository.CurrentlyWatchingRepository
import com.zulfadar.konnetto.data.repository.FriendListRepository
import com.zulfadar.konnetto.data.repository.FriendRequestRepository
import com.zulfadar.konnetto.data.repository.NotificationRepository
import com.zulfadar.konnetto.data.repository.PostRepository

object Injection {
    fun provideRepositoy(): PostRepository {
        return PostRepository.getInstance()
    }

    fun provideCurretnlyWatchingRepository(): CurrentlyWatchingRepository {
        return CurrentlyWatchingRepository.getInstance()
    }

    fun provideNotificationsRepository(): NotificationRepository {
        return NotificationRepository.getInstance()
    }

    fun provideFriendRequestsRepository(): FriendRequestRepository {
        return FriendRequestRepository.getInstance()
    }

    fun provideFriendListRepository(): FriendListRepository {
        return FriendListRepository.getInstance()
    }
}