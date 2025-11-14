package com.konnettoco.konnetto.data.local.model

import androidx.compose.runtime.Immutable

@Immutable
data class FriendList(
    val id: Long,
    val displayname: String,
    val username: String,
    val profilePict: Int?
)
