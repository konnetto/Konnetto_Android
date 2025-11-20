package com.konnettoco.konnetto.data.remote.api

import com.konnettoco.konnetto.data.remote.dto.ApiResponse
import com.konnettoco.konnetto.data.remote.dto.auth.otp.resendotp.ResendOtpRequest
import com.konnettoco.konnetto.data.remote.dto.auth.otp.resendotp.ResendOtpResponse
import com.konnettoco.konnetto.data.remote.dto.auth.otp.verifyotp.VerifyOtpRequest
import com.konnettoco.konnetto.data.remote.dto.auth.otp.verifyotp.VerifyOtpResponse
import com.konnettoco.konnetto.data.remote.dto.auth.register.Data
import com.konnettoco.konnetto.data.remote.dto.auth.register.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): ApiResponse<Data>

    @POST("auth/otp/verify")
    suspend fun verifyOtp(
        @Body request: VerifyOtpRequest
    ): ApiResponse<VerifyOtpResponse>

    @POST("auth/otp/resend")
    suspend fun resendOtp(
        @Body request: ResendOtpRequest
    ): ApiResponse<ResendOtpResponse>
}
