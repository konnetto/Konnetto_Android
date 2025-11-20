package com.konnettoco.konnetto.domain.model

data class RegisterResult(
    val userId: String,
    val otpExpiredAt: String
)
