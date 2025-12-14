package com.konnettoco.konnetto.data.remote.dto.auth.otp.verifyotp

import com.google.gson.annotations.SerializedName

data class VerifyOtpResponse(

	@field:SerializedName("data")
	val data: VerifyOtpData? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class VerifyOtpData(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("refresh_token_expired_at")
	val refreshTokenExpiredAt: String? = null,

	@field:SerializedName("role")
	val role: String? = null
)
