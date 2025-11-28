package com.konnettoco.konnetto.data.remote.api

import com.konnettoco.konnetto.data.remote.dto.ApiResponse
import com.konnettoco.konnetto.data.remote.dto.auth.login.LoginRequest
import com.konnettoco.konnetto.data.remote.dto.auth.login.LoginResponse
import com.konnettoco.konnetto.data.remote.dto.auth.otp.resendotp.ResendOtpRequest
import com.konnettoco.konnetto.data.remote.dto.auth.otp.resendotp.ResendOtpResponse
import com.konnettoco.konnetto.data.remote.dto.auth.otp.verifyotp.VerifyOtpRequest
import com.konnettoco.konnetto.data.remote.dto.auth.otp.verifyotp.VerifyOtpResponse
import com.konnettoco.konnetto.data.remote.dto.auth.register.CheckEmailResponse
import com.konnettoco.konnetto.data.remote.dto.auth.register.CheckUsernameResponse
import com.konnettoco.konnetto.data.remote.dto.auth.register.Data
import com.konnettoco.konnetto.data.remote.dto.auth.register.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): ApiResponse<Data>

    @GET("auth/check-username")
    suspend fun checkUsername(
        @Query("username") username: String
    ): CheckUsernameResponse

    @GET("auth/check-email")
    suspend fun checkEmail(
        @Query("email") email: String
    ): CheckEmailResponse

    @POST("auth/otp/verify")
    suspend fun verifyOtp(
        @Body request: VerifyOtpRequest
    ): VerifyOtpResponse

    @POST("auth/otp/resend")
    suspend fun resendOtp(
        @Body request: ResendOtpRequest
    ): ResendOtpResponse

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

//    @POST("auth/check-token")
//    suspend fun checkToken(
//        @Body request: CheckTokenRequest
//    ): ApiResponse<CheckTokenResponse>
}
