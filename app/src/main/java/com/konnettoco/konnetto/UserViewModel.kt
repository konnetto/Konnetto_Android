package com.konnettoco.konnetto

import androidx.lifecycle.ViewModel
import com.konnettoco.konnetto.data.repository.UserRepository

class UserViewModel(
    private val currentUserRepository: UserRepository
): ViewModel() {
//    private val _uiState: MutableStateFlow<UiState<List<User>>> = MutableStateFlow(UiState.Loading)
//    val uiState: StateFlow<UiState<List<User>>>
//        get() = _uiState
//
//    fun getCurrentUSer() {
//        viewModelScope.launch {
//            currentUserRepository.getUser()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { currentUser ->
//                    _uiState.value = UiState.Success(currentUser)
//                }
//        }
//    }
}