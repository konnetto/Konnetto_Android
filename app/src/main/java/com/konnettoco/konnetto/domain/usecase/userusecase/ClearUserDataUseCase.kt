package com.konnettoco.konnetto.domain.usecase.userusecase

import com.konnettoco.konnetto.domain.repository.UserRepository
import javax.inject.Inject

class ClearUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.clearUserData()
    }
}
