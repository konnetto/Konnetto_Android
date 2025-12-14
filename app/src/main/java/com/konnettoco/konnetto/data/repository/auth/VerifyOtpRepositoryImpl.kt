package com.konnettoco.konnetto.data.repository.auth

import com.konnettoco.konnetto.data.remote.api.AuthApiService
import com.konnettoco.konnetto.data.remote.dto.auth.otp.verifyotp.VerifyOtpRequest
import com.konnettoco.konnetto.domain.model.VerifyOtpResult
import com.konnettoco.konnetto.domain.repository.auth.VerifyOtpRepository
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class VerifyOtpRepositoryImpl @Inject constructor(
    private val api: AuthApiService,
): VerifyOtpRepository {
    override suspend fun verifyOtp(
        userid: String,
        verificationType: String,
        otpCode: String
    ): Result<VerifyOtpResult> {
        return try {
            val response = api.verifyOtp(
                VerifyOtpRequest(
                    userId = userid,
                    verificationType = verificationType,
                    otpCode = otpCode
                )
            )

            val data = response.data
            val token = data?.accessToken
            val refresh = data?.refreshToken
            val refreshExpiredAt = data?.refreshTokenExpiredAt
            val role = data?.role

            if (token.isNullOrBlank() || refresh.isNullOrBlank() || refreshExpiredAt.isNullOrBlank() || role.isNullOrBlank()) {
                return Result.failure(Exception("Incomplete user data received from server."))
            }

            val domain = VerifyOtpResult(
                accessToken = token,
                refreshToken = refresh,
                refreshTokenExpiredAt = refreshExpiredAt,
                role = role
            )

            return Result.success(domain)
        } catch (e: HttpException) {
            val code = e.code()
            val message = when(code) {
                409 -> "Otp already in use."
                400 -> "Invalid OTP Code."
                500 -> "An error occurred on the server."
                else -> "An error occurred. Code: $code"
            }
            Result.failure(Exception(message))
        } catch (e: UnknownHostException) {
            Result.failure(Exception("No internet connection"))
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("Connection timed out, please try again"))
        } catch (e: Exception) {
            Result.failure(Exception("An error occurred, please try again."))
        }
    }

}