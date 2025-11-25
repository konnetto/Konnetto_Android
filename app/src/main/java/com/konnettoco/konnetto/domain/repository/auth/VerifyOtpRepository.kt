package com.konnettoco.konnetto.domain.repository.auth

import com.konnettoco.konnetto.domain.model.VerifyOtpResult

interface VerifyOtpRepository {
    suspend fun verifyOtp(
        userid: String,
        verificationType: String,
        otpCode: String
    ): Result<VerifyOtpResult>
}