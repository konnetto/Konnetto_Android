package com.konnettoco.konnetto.ui.screen.library.mylibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.local.model.MyLibraryItem
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val myLibraryItemRepository: MyLibraryItemRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<MyLibraryItem>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllMyLibraryItems()
    }

    fun getAllMyLibraryItems() {
        viewModelScope.launch {
            myLibraryItemRepository.getAllMyLibraryItems()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { myLibraryItem ->
                    _uiState.value = UiState.Success(myLibraryItem)
                }
        }
    }
}