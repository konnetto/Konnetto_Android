package com.konnettoco.konnetto.ui.screen.library.mylibrarydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.model.MyLibraryItem
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyLibraryDetailViewModel(
    private val libraryItemRepository: MyLibraryItemRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<MyLibraryItem>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MyLibraryItem>>
        get() = _uiState

    fun getLibraryItembyId(libraryItemId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(libraryItemRepository
                .getLibraryItemById(libraryItemId)
            )
        }
    }
}