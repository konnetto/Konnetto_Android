package com.konnettoco.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.ui.screen.library.editprogress.EditProgressViewModel

class EditProgressViewModelFactory (
    private val myLibraryItemRepository: MyLibraryItemRepository,
    private val libraryItemId: Long
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProgressViewModel::class.java)) {
            return EditProgressViewModel(
                myLibraryItemRepository,
                libraryItemId = libraryItemId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}