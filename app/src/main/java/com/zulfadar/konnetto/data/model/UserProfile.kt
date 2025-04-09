package com.zulfadar.konnetto.data.model

data class UserProfile(
    val username: String,
    val profilePict: String?,
    val friends: Int?,
    val follows: Int?,
    val post: Post?,
)
