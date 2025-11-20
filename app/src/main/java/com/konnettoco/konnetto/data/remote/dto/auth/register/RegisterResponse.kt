package com.konnettoco.konnetto.data.remote.dto.auth.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@SerializedName("data")
	val data: Data? = null,

	@SerializedName("timestamp")
	val timestamp: String? = null
)

data class Data(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("otp_expired_at")
	val otpExpiredAt: String? = null
)
