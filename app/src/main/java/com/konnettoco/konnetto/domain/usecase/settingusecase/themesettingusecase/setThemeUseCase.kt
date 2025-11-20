package com.konnettoco.konnetto.domain.usecase.settingusecase.themesettingusecase

import com.konnettoco.konnetto.domain.repository.setting.SettingsRepository
import javax.inject.Inject

class SetThemeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(isDark: Boolean) = repository.setTheme(isDark)
}
