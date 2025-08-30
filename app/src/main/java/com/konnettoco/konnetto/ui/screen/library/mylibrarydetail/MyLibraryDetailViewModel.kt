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
    private val libraryItemRepository: MyLibraryItemRepository,
    private val libraryItemId: Long
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<MyLibraryItem>>(UiState.Loading)
    val uiState: StateFlow<UiState<MyLibraryItem>> = _uiState

    init {
        getLibraryItemById(libraryItemId)
    }

    fun getLibraryItemById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val item = libraryItemRepository.getLibraryItemById(id)
                _uiState.value = UiState.Success(item)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}