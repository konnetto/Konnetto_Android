package com.konnettoco.konnetto.data.remote.dto.auth.otp.requestotp

import com.google.gson.annotations.SerializedName

data class OtpRequest(
    @SerializedName("email_or_username")
    val emailOrUsername: String,

    @SerializedName("verification_type")
    val verificationType: String,
)