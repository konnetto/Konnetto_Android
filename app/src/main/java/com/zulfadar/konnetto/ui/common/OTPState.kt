package com.zulfadar.konnetto.ui.common

data class OTPState(
    val code: List<Int?> = (1..6).map { null },
    val focusIndex: Int? = null,
    val isValid: Boolean? = null
)
