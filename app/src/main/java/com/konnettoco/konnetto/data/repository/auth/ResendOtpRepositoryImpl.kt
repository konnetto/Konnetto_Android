package com.konnettoco.konnetto.data.repository.auth

import com.konnettoco.konnetto.data.remote.api.AuthApiService
import com.konnettoco.konnetto.data.remote.dto.auth.otp.resendotp.ResendOtpRequest
import com.konnettoco.konnetto.domain.model.ResendOtpResult
import com.konnettoco.konnetto.domain.repository.auth.ResendOtpRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ResendOtpRepositoryImpl @Inject constructor(
    private val api: AuthApiService
): ResendOtpRepository {
    override suspend fun resendOtp(
        userId: String,
        verificationType: String
    ): Result<ResendOtpResult> {
        return try {
            val response = api.resendOtp(
                ResendOtpRequest(
                    userId = userId,
                    verificationType = verificationType
                )
            )

            val domain = response.data.let {
                ResendOtpResult(
                    userId = it?.userId ?: "",
                    otpExpiredAt = it?.otpExpiredAt ?: ""
                )
            }

            return Result.success(domain)
        } catch (e: UnknownHostException) {
            Result.failure(Exception("No internet connection"))
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("Connection timed out, please try again"))
        } catch (e: Exception) {
            Result.failure(Exception("An error occurred, please try again."))
        }
    }

}