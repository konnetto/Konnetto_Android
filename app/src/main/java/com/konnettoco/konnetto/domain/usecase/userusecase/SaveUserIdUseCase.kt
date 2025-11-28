package com.konnettoco.konnetto.domain.usecase.userusecase

import com.konnettoco.konnetto.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: String): Flow<Unit> {
        return userRepository.saveUserId(userId)
    }
}
