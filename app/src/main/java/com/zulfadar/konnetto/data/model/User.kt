package com.zulfadar.konnetto.data.model

data class User(
    val id: Long,
    val username: String,
    val name: String,
    val email: String,
    val isCreator: Boolean,
    val photo: String?,
    val header: String?,
    val bio: String?,
    val totalFriends: Int,
    val totalFollowing: Int,
    val totalPost: Int,
    val friend: Boolean,
    val followed: Boolean,
    val post: Post,
    val picks: SugoiPicks,
    val createdAt: String?,
    val updateAt: String?,
) {
    fun asUserProfile() : UserProfile {
        return UserProfile(
            id = id,
            photo = photo,
            username = username,
            name = name,
        )
    }
}
