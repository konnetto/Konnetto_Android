package com.konnettoco.konnetto.data.remote.dto.auth.otp.resendotp

import com.google.gson.annotations.SerializedName

data class ResendOtpResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class Data(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("otp_expired_at")
	val otpExpiredAt: String? = null
)
