package com.zulfadar.konnetto.data.model

data class Auth(
    val profile: UserProfile,
    val accessToken: String,
    val refreshToken: String
)
