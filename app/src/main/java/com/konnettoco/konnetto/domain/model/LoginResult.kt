package com.konnettoco.konnetto.domain.model

data class LoginResult(
    val accessToken: String,
    val refreshToken: String,
    val role: String,
    //val refreshTokenExpiredAt: String? = null,
)
