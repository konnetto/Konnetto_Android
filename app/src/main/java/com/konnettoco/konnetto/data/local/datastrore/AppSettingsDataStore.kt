package com.konnettoco.konnetto.data.local.datastrore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val PREFS_NAME = "settings_pref"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFS_NAME)

@Singleton
class AppSettingsDataStore @Inject constructor(
    private val context: Context
) {
    companion object {
        val  KEY_DARK_MODE = booleanPreferencesKey("key_dark_mode")
    }

    val settingsFlow: Flow<SettingsDto> = context.dataStore.data
        .map { prefs ->
            SettingsDto(
                darkMode = prefs[KEY_DARK_MODE] ?: false
            )
        }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DARK_MODE] = isDarkMode
        }
    }
}

data class SettingsDto(
    val darkMode: Boolean
)