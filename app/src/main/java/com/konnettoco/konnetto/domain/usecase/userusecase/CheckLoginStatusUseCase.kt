package com.konnettoco.konnetto.domain.usecase.userusecase

import com.konnettoco.konnetto.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return userRepository.isLoggedIn()
    }
}
