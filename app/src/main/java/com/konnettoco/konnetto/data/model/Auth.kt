package com.konnettoco.konnetto.data.model

data class Auth(
    val profile: UserProfile,
    val accessToken: String,
    val refreshToken: String
)
