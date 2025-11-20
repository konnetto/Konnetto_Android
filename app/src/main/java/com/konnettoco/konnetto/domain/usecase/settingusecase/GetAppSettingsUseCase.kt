package com.konnettoco.konnetto.domain.usecase.settingusecase

import com.konnettoco.konnetto.domain.model.AppSettings
import com.konnettoco.konnetto.domain.repository.setting.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppSettingsUseCase @Inject constructor(
    private val  repository: SettingsRepository
) {
    operator fun invoke(): Flow<AppSettings> =repository.getSettings()
}