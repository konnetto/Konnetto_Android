package com.konnettoco.konnetto.di

import com.konnettoco.konnetto.data.repository.CurrentlyWatchingRepository
import com.konnettoco.konnetto.data.repository.FriendListRepository
import com.konnettoco.konnetto.data.repository.FriendRequestRepository
import com.konnettoco.konnetto.data.repository.NotificationRepository
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.data.repository.UserRepository

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

    fun provideCurrentUserRepository(): UserRepository {
        return UserRepository.getInstance()
    }
}