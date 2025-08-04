package com.konnettoco.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.data.repository.SugoiPicksRepository
import com.konnettoco.konnetto.ui.screen.home.HomeViewModel

class ViewModelFactory(
    private val repository: PostRepository,
    private val sugoiPicksRepository: SugoiPicksRepository,
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                repository,
                sugoiPicksRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}