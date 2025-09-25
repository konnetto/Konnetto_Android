package com.konnettoco.konnetto.ui.screen.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val Context.dataStore by preferencesDataStore("settings")

class SettingsViewModel(
    private val context: Context
) : ViewModel() {
    private val THEME_KEY = booleanPreferencesKey("dark_mode")

    private val _isThemeLoaded = MutableStateFlow(false)
    val isThemeLoaded: StateFlow<Boolean> = _isThemeLoaded.asStateFlow()

    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[THEME_KEY] ?: false }
        .onEach { _isThemeLoaded.value = true } // ✅ tandai sudah load
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

//    val isDarkTheme: Flow<Boolean> = context.dataStore.data
//        .map { preferences -> preferences[THEME_KEY] ?: false }

    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[THEME_KEY] = isDark
            }
        }
    }
}