package com.konnettoco.konnetto.domain.usecase.authusecase.otpusecase

import com.konnettoco.konnetto.domain.model.ResendOtpResult
import com.konnettoco.konnetto.domain.repository.auth.ResendOtpRepository
import javax.inject.Inject

class ResendOtpUseCase @Inject constructor(
    private val repository: ResendOtpRepository
) {
    suspend operator fun invoke(userId: String, verificationType: String): Result<ResendOtpResult> {
        return repository.resendOtp(
            userId = userId,
            verificationType = verificationType
        )
    }
}