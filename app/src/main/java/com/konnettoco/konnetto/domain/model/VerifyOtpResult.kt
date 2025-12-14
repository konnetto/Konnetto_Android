package com.konnettoco.konnetto.domain.model

data class VerifyOtpResult(
    val accessToken: String,
    val refreshToken: String,
    val refreshTokenExpiredAt: String,
    val role: String,
)