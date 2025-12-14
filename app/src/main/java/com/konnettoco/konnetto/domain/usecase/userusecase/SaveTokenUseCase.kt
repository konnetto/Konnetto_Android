package com.konnettoco.konnetto.domain.usecase.userusecase

import com.konnettoco.konnetto.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(accessToken: String, refreshToken: String, refreshTokenExpiredAt: String, userRole: String): Flow<Unit> {
        return userRepository.saveAuthToken(accessToken, refreshToken, refreshTokenExpiredAt, userRole)
    }
}
