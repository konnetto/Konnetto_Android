package com.konnettoco.konnetto.ui.screen.auth.otppages

data class OTPState(
    val code: List<Int?> = List(6) { null },
    val focusIndex: Int = 0,
    val isValid: Boolean? = null,

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,

    val accessToken: String? = null,
    val refreshToken: String? = null,
    val role: String? = null,

    // resend timer
    val resendRemainingSeconds: Int = 0,
    val isResendEnabled: Boolean = true,

    val isButtonEnabled: Boolean = false
)
