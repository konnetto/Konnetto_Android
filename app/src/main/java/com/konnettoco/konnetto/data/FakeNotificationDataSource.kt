package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.local.model.NotificationsTile

object FakeNotificationDataSource {
    val dummyNotification = listOf(
        NotificationsTile(
            notificationId = 0,
            notificationTitle = "liked your post",
            notificationUsername = "Wi wok de tok",
            notificationTimeStamp = "4h",
            notificationProfile = R.drawable.logo,
            notificationCategory = "like"
        ),
        NotificationsTile(
            notificationId = 1,
            notificationTitle = "liked your comment",
            notificationUsername = "Fufufafa",
            notificationTimeStamp = "5h",
            notificationProfile = R.drawable.logo,
            notificationCategory = "likedComment"
        ),
        NotificationsTile(
            notificationId = 2,
            notificationTitle = "send you friend request",
            notificationUsername = "Wok Wok",
            notificationTimeStamp = "6h",
            notificationProfile = R.drawable.logo,
            notificationCategory = "askFriendRequest"
        ),
        NotificationsTile(
            notificationId = 3,
            notificationTitle = "accepted your friend request",
            notificationUsername = "Uzumaki Saburo",
            notificationTimeStamp = "7h",
            notificationProfile = R.drawable.logo,
            notificationCategory = "acceptedFriendRequest"
        ),
        NotificationsTile(
            notificationId = 4,
            notificationTitle = "just reply your comment",
            notificationUsername = "Wi wok de tok",
            notificationTimeStamp = "8h",
            notificationProfile = R.drawable.logo,
            notificationCategory = "commentReply"
        ),
        NotificationsTile(
            notificationId = 5,
            notificationTitle = "comment on your post",
            notificationUsername = "Fufufafa",
            notificationTimeStamp = "5h",
            notificationProfile = R.drawable.logo,
            notificationCategory = "comment"
        ),
    )
}