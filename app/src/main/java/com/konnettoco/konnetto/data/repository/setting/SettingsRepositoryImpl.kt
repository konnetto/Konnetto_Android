package com.konnettoco.konnetto.data.repository.setting

import com.konnettoco.konnetto.data.local.datastrore.AppSettingsDataStore
import com.konnettoco.konnetto.domain.model.AppSettings
import com.konnettoco.konnetto.domain.repository.setting.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: AppSettingsDataStore
) : SettingsRepository {
    override fun getSettings(): Flow<AppSettings> = dataStore.settingsFlow.map { dto ->
        AppSettings(
            isDarkMode = dto.darkMode
        )
    }

    override fun getTheme(): Flow<Boolean> = dataStore.settingsFlow.map { it.darkMode}

    override suspend fun setTheme(isDarkMode: Boolean) {
        dataStore.setDarkMode(isDarkMode)
    }
}