package com.konnettoco.konnetto.domain.repository.auth

import com.konnettoco.konnetto.domain.model.ResendOtpResult

interface ResendOtpRepository {
    suspend fun resendOtp(userId: String, verificationType: String): Result<ResendOtpResult>
}
