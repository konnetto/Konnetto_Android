package com.konnettoco.konnetto.domain.model

data class ResendOtpResult(
    val userId: String,
    val otpExpiredAt: String,
)
