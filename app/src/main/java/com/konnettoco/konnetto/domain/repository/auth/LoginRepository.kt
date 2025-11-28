package com.konnettoco.konnetto.domain.repository.auth

import com.konnettoco.konnetto.domain.model.LoginResult

interface LoginRepository {
    suspend fun login(
        emailOrUserName: String,
        password: String
    ): Result<LoginResult>
}