package com.konnettoco.konnetto.domain.usecase.authusecase.otpusecase

import com.konnettoco.konnetto.domain.model.VerifyOtpResult
import com.konnettoco.konnetto.domain.repository.auth.VerifyOtpRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(
    private val repository: VerifyOtpRepository
) {
    suspend operator fun invoke(
        userid: String,
        verificationType: String,
        otpCode: String
    ): Result<VerifyOtpResult> {
        return repository.verifyOtp(
            userid = userid,
            verificationType = verificationType,
            otpCode = otpCode
        )
    }
}