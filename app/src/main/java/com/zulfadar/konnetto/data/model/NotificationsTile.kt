package com.zulfadar.konnetto.data.model

data class NotificationsTile(
    val notificationId: Long,
    val notificationTitle: String,
    val notificationProfile: Int,
    val notificationUsername: String,
    val notificationTimeStamp: String,
    val notificationCategory: String,
)
