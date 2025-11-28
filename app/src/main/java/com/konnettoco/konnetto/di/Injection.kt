package com.konnettoco.konnetto.di

import com.konnettoco.konnetto.data.repository.FriendListRepository
import com.konnettoco.konnetto.data.repository.FriendRequestRepository
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.data.repository.NotificationRepository
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.data.repository.SugoiPicksRepository
import com.konnettoco.konnetto.data.repository.UserDummyRepository

object Injection {
    fun provideRepositoy(): PostRepository {
        return PostRepository.getInstance()
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

    fun provideUserRepository(): UserDummyRepository {
        return UserDummyRepository.getInstance()
    }

    fun provideSugoiPicksRepository(): SugoiPicksRepository {
        return SugoiPicksRepository.getInstance()
    }

    fun provideLibraryRepository(): MyLibraryItemRepository {
        return MyLibraryItemRepository.getInstance()
    }
}