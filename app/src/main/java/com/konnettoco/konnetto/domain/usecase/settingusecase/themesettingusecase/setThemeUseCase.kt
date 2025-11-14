package com.konnettoco.konnetto.domain.usecase.settingusecase.themesettingusecase

import com.konnettoco.konnetto.domain.repository.SettingsRepository
import javax.inject.Inject

class SetThemeUseCase @Inject constructor(
    private val repo: SettingsRepository
) {
    suspend operator fun invoke(isDark: Boolean) = repo.setTheme(isDark)
}
