package com.konnettoco.konnetto.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.domain.usecase.settingusecase.themesettingusecase.GetThemeUseCase
import com.konnettoco.konnetto.domain.usecase.settingusecase.themesettingusecase.SetThemeUseCase
import com.konnettoco.konnetto.domain.usecase.userusecase.ClearUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase,
    private val clearUserDataUseCase: ClearUserDataUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _logoutCompleted = MutableStateFlow(false)
    val logoutCompleted = _logoutCompleted.asStateFlow()

    val isDarkTheme: StateFlow<Boolean> = getThemeUseCase()
        .onEach {
            _isLoading.value = false
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    fun toggleTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            setThemeUseCase(isDarkMode)
        }
    }

    fun logout() {
        viewModelScope.launch {
            clearUserDataUseCase()
            _logoutCompleted.value = true
        }
    }

    fun onLogoutCompleted() {
        _logoutCompleted.value = false
    }
}