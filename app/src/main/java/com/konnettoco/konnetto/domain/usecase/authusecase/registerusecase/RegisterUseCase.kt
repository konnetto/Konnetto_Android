package com.konnettoco.konnetto.domain.usecase.authusecase.registerusecase

import com.konnettoco.konnetto.domain.model.RegisterResult
import com.konnettoco.konnetto.domain.repository.auth.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(
        fullName: String,
        email: String,
        username: String,
        password: String
    ): Result<RegisterResult> {
        return repository.register(
            fullName,
            email,
            username,
            password
        )
    }
}