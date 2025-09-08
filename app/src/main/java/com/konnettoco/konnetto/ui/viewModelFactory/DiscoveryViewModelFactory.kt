package com.konnettoco.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.data.repository.SugoiPicksRepository
import com.konnettoco.konnetto.ui.screen.discovery.DiscoveryViewModel

class DiscoveryViewModelFactory (
   private val sugoiPicksRepository: SugoiPicksRepository,
   private val animeListRepository: MyLibraryItemRepository,
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiscoveryViewModel::class.java)) {
            return DiscoveryViewModel(
                animeListRepository,
                sugoiPicksRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}