package com.konnettoco.konnetto.data.local.model

data class FriendRequest(
    val id: Long,
    val displayname: String,
    val username: String,
    val profilePict: Int,
    val timeStamp: Int,
)
