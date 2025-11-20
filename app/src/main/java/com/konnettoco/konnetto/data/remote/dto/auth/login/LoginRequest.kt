package com.konnettoco.konnetto.data.remote.dto.auth.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email_or_username")
    val emailOrUserName: String,

    @SerializedName("password")
    val password: String
)
