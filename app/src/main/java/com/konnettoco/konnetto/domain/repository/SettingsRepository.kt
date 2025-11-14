package com.konnettoco.konnetto.domain.repository

import com.konnettoco.konnetto.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<AppSettings>
    fun getTheme(): Flow<Boolean>
    suspend fun setTheme(isDarkMode: Boolean)
}