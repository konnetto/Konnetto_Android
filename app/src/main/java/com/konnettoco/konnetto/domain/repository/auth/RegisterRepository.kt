package com.konnettoco.konnetto.domain.repository.auth

import com.konnettoco.konnetto.domain.model.RegisterResult

interface RegisterRepository {
    suspend fun register(
        fullName: String,
        email: String,
        username: String,
        password: String
    ): Result<RegisterResult>
}