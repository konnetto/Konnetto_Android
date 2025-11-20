package com.konnettoco.konnetto.data.mapper

import com.konnettoco.konnetto.data.remote.dto.auth.register.RegisterResponse
import com.konnettoco.konnetto.domain.model.RegisterResult

fun RegisterResponse.toDomain(): RegisterResult? {
    val d = this.data ?: return null

    return RegisterResult(
        userId = d.userId ?: "",
        otpExpiredAt = d.otpExpiredAt ?: ""
    )
}