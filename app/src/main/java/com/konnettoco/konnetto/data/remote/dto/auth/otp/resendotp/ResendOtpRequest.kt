package com.konnettoco.konnetto.data.remote.dto.auth.otp.resendotp

import com.google.gson.annotations.SerializedName

data class ResendOtpRequest(
    @SerializedName( "user_id")
    val userId: String,

    @SerializedName("verification_type")
    val verificationType: String,
)
