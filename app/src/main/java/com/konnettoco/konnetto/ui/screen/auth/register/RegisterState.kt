package com.konnettoco.konnetto.ui.screen.auth.register

data class RegisterState(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,

    val userId: String? = null,
    val otpExpiredAt: String? = null,

    val errorName: String? = null,
    val errorUsername: String? = null,
    val errorEmail: String? = null,
    val errorPassword: String? = null,
    val errorConfirmPassword: String? = null,

    val isUsernameAvailable: Boolean? = null,
    val isEmailAvailable: Boolean? = null
)
