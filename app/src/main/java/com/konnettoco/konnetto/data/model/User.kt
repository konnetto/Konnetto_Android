package com.konnettoco.konnetto.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val id: Long,
    val username: String,
    val displayname: String,
    val email: String,
    val isCurrentUser: Boolean = false,
    val isCreator: Boolean = false,
    val isFriend: Boolean = false,
    val photo: String? = null,
    val bio: String? = null,
    val totalFriends: Int = 0,
    val totalFollowing: Int = 0,
    val totalPost: Int = 0,
    val totalWatch: Int = 0,
    val friend: Boolean = false,
    val followed: Boolean = false,
    val post: List<Post> = emptyList(),
    val picks: List<SugoiPicks> = emptyList(),
    val createdAt: String? = null,
    val updatedAt: String? = null
)
