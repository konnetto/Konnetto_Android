package com.konnettoco.konnetto.domain.usecase.userusecase

import com.konnettoco.konnetto.domain.repository.UserRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(accessToken: String, refreshToken: String, userId: String, userRole: String) {
        userRepository.saveToken(accessToken, refreshToken, userId, userRole)
    }
}
