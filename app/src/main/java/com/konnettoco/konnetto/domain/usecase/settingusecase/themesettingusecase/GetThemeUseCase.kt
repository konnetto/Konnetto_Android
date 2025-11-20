package com.konnettoco.konnetto.domain.usecase.settingusecase.themesettingusecase

import com.konnettoco.konnetto.domain.repository.setting.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.getTheme()
}
