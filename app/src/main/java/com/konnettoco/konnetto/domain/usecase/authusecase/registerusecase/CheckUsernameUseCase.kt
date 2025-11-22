package com.konnettoco.konnetto.domain.usecase.authusecase.registerusecase

import com.konnettoco.konnetto.domain.repository.auth.RegisterRepository
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(username: String): Result<Unit> {
        return repository.checkUsername(username)
    }
}
