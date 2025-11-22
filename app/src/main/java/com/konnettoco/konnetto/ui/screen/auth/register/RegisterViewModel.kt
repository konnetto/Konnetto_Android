package com.konnettoco.konnetto.ui.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.domain.usecase.authusecase.registerusecase.CheckEmailUseCase
import com.konnettoco.konnetto.domain.usecase.authusecase.registerusecase.CheckUsernameUseCase
import com.konnettoco.konnetto.domain.usecase.authusecase.registerusecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val checkUsernameUseCase: CheckUsernameUseCase,
    private val checkEmailUseCase: CheckEmailUseCase
): ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    private var checkUsernameJob: Job? = null
    private var checkEmailJob: Job? = null

    fun onNameChange(value: String) {
        _registerState.value = _registerState.value.copy(
            name = value
        )
    }

    fun onUsernameChange(value: String) {
        val cleanUsername = value.lowercase().filterNot { it.isWhitespace() }

        _registerState.value = _registerState.value.copy(
            username = cleanUsername,
            errorUsername = null,
            isUsernameAvailable = null
        )

        checkUsernameJob?.cancel()
        checkUsernameJob = viewModelScope.launch {
            delay(500)

            if (!isUsernameValid(cleanUsername)) {
                _registerState.value = _registerState.value.copy(
                    errorUsername = "Invalid username format",
                    isUsernameAvailable = false
                )
                return@launch
            }

            checkUsernameUseCase(cleanUsername)
                .onSuccess {
                    _registerState.value = _registerState.value.copy(
                        errorUsername = null,
                        isUsernameAvailable = true
                    )
                }
                .onFailure { e ->
                    _registerState.value = _registerState.value.copy(
                        errorUsername = e.message,
                        isUsernameAvailable = false
                    )
                }
        }
    }

    fun onEmailChange(value: String) {
        val cleanEmail = value.filterNot { it.isWhitespace() }

        _registerState.value = _registerState.value.copy(
            email = cleanEmail,
            isEmailAvailable = null
        )

        checkEmailJob?.cancel()
        checkEmailJob = viewModelScope.launch {
            delay(500)
            if (!cleanEmail.contains("@") || !cleanEmail.contains(".com") ) {
                _registerState.value = _registerState.value.copy(
                    errorEmail = "Invalid email",
                    isEmailAvailable = false
                )
                return@launch
            }

            checkEmailUseCase(cleanEmail)
                .onSuccess {
                    _registerState.value = _registerState.value.copy(
                        errorEmail = null,
                        isEmailAvailable = true
                    )
                }
                .onFailure { e ->
                    _registerState.value = _registerState.value.copy(
                        errorEmail = e.message,
                        isEmailAvailable = false
                    )
                }
        }
    }

    fun onPasswordChange(value: String) {
        val currentState = _registerState.value
        val passwordMatches = value == currentState.confirmPassword
        _registerState.value = currentState.copy(
            password = value,
            errorConfirmPassword = if (currentState.confirmPassword.isNotEmpty() && !passwordMatches) "Password does not match" else null
        )
    }

    fun onConfirmPasswordChange(value: String) {
        val currentState = _registerState.value
        val passwordMatches = value == currentState.password
        _registerState.value = currentState.copy(
            confirmPassword = value,
            errorConfirmPassword = if (!passwordMatches) "Password does not match" else null
        )
    }

    private fun isUsernameValid(username: String): Boolean {
        if (username.length !in 3..20) return false
        if (!username.matches(Regex("^[a-z0-9._]+$"))) return false
        if (username.startsWith(".") || username.startsWith("_") || username.endsWith(".") || username.endsWith("_")) return false
        if (username.contains("..") || username.contains("__") || username.contains("._") || username.contains("_.")) return false
        return !username.any { it.code > 127 }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8 && !password.contains(" ")
    }

    fun register() {
        val state = registerState.value
        var hasError = false

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
            _registerState.value = _registerState.value.copy(errorConfirmPassword = "Password does not match")
            hasError = true
        }

        if (hasError) return

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
}
