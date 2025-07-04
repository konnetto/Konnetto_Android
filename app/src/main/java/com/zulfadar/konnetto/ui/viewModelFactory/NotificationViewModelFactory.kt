package com.zulfadar.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulfadar.konnetto.data.repository.NotificationRepository
import com.zulfadar.konnetto.ui.screen.notification.NotificationViewModel

class NotificationViewModelFactory(
    private val notificationRepository: NotificationRepository,
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            return NotificationViewModel(notificationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}