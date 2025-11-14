package com.konnettoco.konnetto.ui.screen.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.data.local.model.MyLibraryItem
import com.konnettoco.konnetto.data.local.model.SugoiPicks
import com.konnettoco.konnetto.data.repository.MyLibraryItemRepository
import com.konnettoco.konnetto.data.repository.SugoiPicksRepository
import com.konnettoco.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DiscoveryViewModel(
    private val animeListRepository: MyLibraryItemRepository,
    private val sugoiPicksRepository: SugoiPicksRepository,
): ViewModel() {
    private val _sugoiPicksState = MutableStateFlow<UiState<List<SugoiPicks>>>(UiState.Loading)
    val sugoiPicksState = _sugoiPicksState.asStateFlow()

    private val _animeListState = MutableStateFlow<UiState<List<MyLibraryItem>>>(UiState.Loading)
    val animeListState = _animeListState.asStateFlow()

    init {
        getAllSugoiPicks()
        getAllAnimes()
    }

    fun getAllSugoiPicks() {
        viewModelScope.launch {
            sugoiPicksRepository.getAllSugoiPicks()
                .catch { e -> _sugoiPicksState.value = UiState.Error(e.message ?: "Unknown Error") }
                .collect { picks -> _sugoiPicksState.value = UiState.Success(picks) }
        }
    }

    fun getAllAnimes() {
        viewModelScope.launch {
            animeListRepository.getAllMyLibraryItems()
                .catch { e -> _animeListState.value = UiState.Error(e.message ?: "Unknown Error") }
                .collect { animes -> _animeListState.value = UiState.Success(animes) }
        }
    }
}