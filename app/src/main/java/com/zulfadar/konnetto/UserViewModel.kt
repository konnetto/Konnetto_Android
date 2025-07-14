package com.zulfadar.konnetto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.konnetto.data.model.User
import com.zulfadar.konnetto.data.repository.UserRepository
import com.zulfadar.konnetto.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(
    private val currentUserRepository: UserRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<User>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<User>>>
        get() = _uiState

    fun getCurrentUSer() {
        viewModelScope.launch {
            currentUserRepository.getCurrentUser()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { currentUser ->
                    _uiState.value = UiState.Success(currentUser)
                }
        }
    }
}