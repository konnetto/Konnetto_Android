package com.konnettoco.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.ui.screen.library.mylibrarydetail.MyLibraryDetailViewModel

class LibraryItemDetailViewModelFactory (
    private val myLibraryItemRepository: MyLibraryItemRepository,
    private val libraryItemId: Long
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyLibraryDetailViewModel::class.java)) {
            return MyLibraryDetailViewModel(
                myLibraryItemRepository,
                libraryItemId = libraryItemId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}