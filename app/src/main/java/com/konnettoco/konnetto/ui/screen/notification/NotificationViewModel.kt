package com.konnettoco.konnetto.ui.screen.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.local.model.NotificationsTile
import com.konnettoco.konnetto.data.repository.NotificationRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<NotificationsTile>>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllNotifications()
    }

    fun getAllNotifications() {
        viewModelScope.launch {
            notificationRepository.getAllNotifications()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { notifications ->
                    _uiState.value = UiState.Success(notifications)
                }
        }
    }
}