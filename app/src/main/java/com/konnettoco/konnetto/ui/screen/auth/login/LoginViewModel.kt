package com.konnettoco.konnetto.ui.screen.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.domain.model.LoginResult
import com.konnettoco.konnetto.domain.usecase.authusecase.loginusecase.LoginUseCase
import com.konnettoco.konnetto.domain.usecase.userusecase.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun onEmailOrPasswordChange(value: String) {
        _loginState.update { it.copy(emailOrUsername = value) }
    }

    fun onPasswordChange(value: String) {
        _loginState.update { it.copy(password = value) }
    }

    fun login() {
        val state = loginState.value
        
        // Clear previous errors
        _loginState.update { it.copy(errorEmailOrUsername = null, errorPassword = null, error = null) }

        val isEmailOrUsernameInvalid = state.emailOrUsername.isBlank()
        val isPasswordInvalid = state.password.isBlank()

        if (isEmailOrUsernameInvalid || isPasswordInvalid) {
            _loginState.update {
                it.copy(
                    errorEmailOrUsername = if (isEmailOrUsernameInvalid) "Email or username cannot be empty" else null,
                    errorPassword = if (isPasswordInvalid) "Password cannot be empty" else null
                )
            }
            return
        }

        viewModelScope.launch {
            _loginState.update { it.copy(isLoading = true) }

            loginUseCase(
                emailOrUserName = state.emailOrUsername,
                password = state.password
            ).onSuccess { loginResult ->
                handleLoginSuccess(loginResult)
            }.onFailure { throwable ->
                handleLoginFailure(throwable)
            }
        }
    }

    private suspend fun handleLoginSuccess(loginResult: LoginResult) {
        Log.d("AuthDebug", "Login success. AccessToken: ${loginResult.accessToken}")
        runCatching {
            saveTokenUseCase(
                accessToken = loginResult.accessToken,
                refreshToken = loginResult.refreshToken,
                userRole = loginResult.role
            ).collect()
        }.onSuccess {
            Log.d("AuthDebug", "saveTokenUseCase finished.")
            _loginState.update {
                it.copy(
                    isLoading = false,
                    isSuccess = true,
                    accessToken = loginResult.accessToken,
                    refreshToken = loginResult.refreshToken,
                    role = loginResult.role
                )
            }
        }.onFailure { e ->
            Log.e("AuthDebug", "Failed to save token", e)
            _loginState.update { state ->
                state.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = "Failed to save token"
                )
            }
        }
    }

    private fun handleLoginFailure(throwable: Throwable) {
        Log.e("AuthDebug", "Login API call failed", throwable)
        _loginState.update {
            it.copy(
                isLoading = false,
                isSuccess = false,
                error = throwable.message ?: "Login failed"
            )
        }
    }
}
