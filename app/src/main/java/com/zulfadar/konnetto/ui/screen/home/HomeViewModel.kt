package com.zulfadar.konnetto.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.konnetto.data.model.Posting
import com.zulfadar.konnetto.data.repository.PostRepository
import com.zulfadar.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PostRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Posting>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Posting>>>
        get() = _uiState

    fun getAllPostings() {
        viewModelScope.launch {
            repository.getAllPosting()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { postings ->
                    _uiState.value = UiState.Success(postings)
                }
        }
    }
}