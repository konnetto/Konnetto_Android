package com.konnettoco.konnetto.domain.usecase.authusecase.loginusecase

import com.konnettoco.konnetto.domain.model.LoginResult
import com.konnettoco.konnetto.domain.repository.auth.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(
        emailOrUserName: String,
        password: String
    ): Result<LoginResult> {
        return repository.login(
            emailOrUserName,
            password
        )
    }
}