package com.konnettoco.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konnettoco.konnetto.data.repository.CurrentlyWatchingRepository
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.ui.screen.profile.userprofile.ProfileViewModel

class ProfileViewModelFactory(
    private val repository: PostRepository,
    private val currentlyWatchingRepository: CurrentlyWatchingRepository,
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                repository,
                currentlyWatchingRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}