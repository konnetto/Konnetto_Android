package com.konnettoco.konnetto.data.remote.dto.auth.otp.verifyotp

import com.google.gson.annotations.SerializedName

data class VerifyOtpRequest(
    @SerializedName("user_id")
    val userId: String,

    @SerializedName("verification_type")
    val verificationType: String,

    @SerializedName("otp_code")
    val otpCode: String
)
