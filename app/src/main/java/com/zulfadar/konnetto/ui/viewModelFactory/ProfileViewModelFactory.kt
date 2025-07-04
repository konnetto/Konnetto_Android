package com.zulfadar.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulfadar.konnetto.data.repository.CurrentlyWatchingRepository
import com.zulfadar.konnetto.data.repository.PostRepository
import com.zulfadar.konnetto.ui.screen.profile.ProfileViewModel

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