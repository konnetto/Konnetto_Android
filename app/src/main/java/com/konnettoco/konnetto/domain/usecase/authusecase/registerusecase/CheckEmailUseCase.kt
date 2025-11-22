package com.konnettoco.konnetto.domain.usecase.authusecase.registerusecase

import com.konnettoco.konnetto.domain.repository.auth.RegisterRepository
import javax.inject.Inject

class CheckEmailUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(email: String): Result<Unit> {
        return repository.checkEmail(email)
    }
}
