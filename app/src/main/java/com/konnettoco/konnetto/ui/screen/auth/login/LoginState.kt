package com.konnettoco.konnetto.ui.screen.auth.login

data class LoginState(
    val emailOrUsername: String = "",
    val password: String = "",

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,

    val accessToken: String? = null,
    val refreshToken: String? = null,
    val role: String? = null,

    val errorEmailOrUsername: String? = null,
    val errorPassword: String? = null,

    val isPasswordValid: Boolean = false
)
