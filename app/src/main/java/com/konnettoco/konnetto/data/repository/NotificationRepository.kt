package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.FakeNotificationDataSource
import com.konnettoco.konnetto.data.local.model.NotificationsTile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NotificationRepository {
    private val notifications = mutableListOf<NotificationsTile>()

    init {
        if (notifications.isEmpty()) {
            FakeNotificationDataSource.dummyNotification.forEach {
                notifications.add(it)
            }
        }
    }

    fun getAllNotifications(): Flow<List<NotificationsTile>> {
        return flowOf(notifications)
    }

    companion object {
        @Volatile
        private var instance: NotificationRepository? = null

        fun getInstance(): NotificationRepository =
            instance ?: synchronized(this) {
                NotificationRepository().apply {
                    instance = this
                }
            }
    }
}