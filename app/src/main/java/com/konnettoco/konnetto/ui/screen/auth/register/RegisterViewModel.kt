package com.konnettoco.konnetto.ui.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.domain.usecase.authusecase.registerusecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun onNameChange(value: String) {
        _registerState.value = _registerState.value.copy(
            name = value
        )
    }

    fun onUsernameChange(value: String) {
        _registerState.value = _registerState.value.copy(
            username = value.lowercase().filterNot { it.isWhitespace() }
        )
    }

    fun onEmailChange(value: String) {
        _registerState.value = _registerState.value.copy(
            email = value.filterNot { it.isWhitespace() }
        )
    }

    fun onPasswordChange(value: String) {
        _registerState.value = _registerState.value.copy(
            password = value
        )
    }

    fun onConfirmPasswordChange(value: String) {
        _registerState.value = _registerState.value.copy(
            confirmPassword = value
        )
    }

    // VALIDATION
    private fun isUsernameValid(username: String): Boolean {
        // normalize to lowercase & remove whitespace
        val cleanUsername = username.lowercase().filterNot { it.isWhitespace() }

        // length check
        if (cleanUsername.length !in 3..20) return false

        // check valid characters: only letter, number, underscore, dot
        if (!cleanUsername.matches(Regex("^[a-z0-9._]+$"))) return false

        // Checks do not start or end with . or _
        if (cleanUsername.startsWith(".") || cleanUsername.startsWith("_")
            || cleanUsername.endsWith(".") || cleanUsername.endsWith("_")
        ) return false

        // Check that there are no two consecutive special chars (.., __, ._, _.)
        if (cleanUsername.contains("..") ||
            cleanUsername.contains("__") ||
            cleanUsername.contains("._") ||
            cleanUsername.contains("_.")
        ) return false

        // Check there are no emojis / non-ASCII characters
        if (cleanUsername.any { it.code > 127 }) return false

        return true
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8 && !password.contains(" ")
    }

    fun register() {
        val state = registerState.value
        var hasError = false

        // Reset error
        _registerState.value = state.copy(
            errorName = null,
            errorUsername = null,
            errorEmail = null,
            errorPassword = null,
            errorConfirmPassword = null
        )

        if (state.name.isBlank()) {
            _registerState.value = _registerState.value.copy(errorName = "Name cannot be empty")
            hasError = true
        }

        if (!isUsernameValid(state.username)) {
            _registerState.value = _registerState.value.copy(errorUsername = "Invalid username format")
            hasError = true
        }

        if (state.email.isBlank() || !state.email.contains("@")) {
            _registerState.value = _registerState.value.copy(errorEmail = "Invalid email")
            hasError = true
        }

        if (!isPasswordValid(state.password)) {
            _registerState.value = _registerState.value.copy(errorPassword = "Password must be 8 chars & no spaces")
            hasError = true
        }

        if (state.password != state.confirmPassword) {
            _registerState.value =
                _registerState.value.copy(errorConfirmPassword = "Password does not match")
            hasError = true
        }

        if (hasError) return

        // API CALL
        viewModelScope.launch {
            _registerState.value = _registerState.value.copy(isLoading = true, error = null)

            val result = registerUseCase(
                fullName = state.name,
                email = state.email,
                username = state.username,
                password = state.password
            )

            result.onSuccess { registerResult ->
                _registerState.value = _registerState.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    userId = registerResult.userId,
                    otpExpiredAt = registerResult.otpExpiredAt
                )
            }.onFailure { e ->
                val msg = e.message ?: "An error occurred, please try again."
                _registerState.value = _registerState.value.copy(
                    isLoading = false,
                    error = msg
                )
            }
        }
    }

    fun resetSuccess() {
        _registerState.value = _registerState.value.copy(isSuccess = false)
    }

//    private fun setError(message: String) {
//        _registerState.value = _registerState.value.copy(
//            error = message,
//            isLoading = false
//        )
//    }
}