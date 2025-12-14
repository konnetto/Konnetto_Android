package com.konnettoco.konnetto.ui.screen.auth.otppages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konnettoco.konnetto.domain.model.VerifyOtpResult
import com.konnettoco.konnetto.domain.usecase.authusecase.otpusecase.ResendOtpUseCase
import com.konnettoco.konnetto.domain.usecase.authusecase.otpusecase.VerifyOtpUseCase
import com.konnettoco.konnetto.domain.usecase.userusecase.SaveTokenUseCase
import com.konnettoco.konnetto.domain.usecase.userusecase.SaveUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase,
    private val resendOtpUseCase: ResendOtpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(OTPState())
    val uiState = _uiState.asStateFlow()

    private var resendJob: Job? = null

    fun onAction(action: OtpAction) {
        when (action) {
            is OtpAction.OnChangeFieldFocused -> {
                _uiState.update { it.copy(focusIndex = action.index.coerceIn(0, 5)) }
            }
            is OtpAction.OnEnterNumber -> {
                handleEnterNumber(action.number, action.index)
            }
            OtpAction.OnKeyboardBack -> handleBackspace()
            is OtpAction.OnPaste -> handlePaste(action.code)
            OtpAction.OnResend -> {
                _uiState.update { it.copy(errorMessage = "Resend failed: User context is missing.") }
            }
        }
    }

    private fun handleEnterNumber(number: Int?, index: Int) {
        val idx = index.coerceIn(0, 5)
        val new = _uiState.value.code.toMutableList()
        new[idx] = number
        val next = when {
            number == null -> idx
            idx == 5 -> 5
            else -> {
                val firstEmpty = new.indexOfFirst { it == null }
                if (firstEmpty == -1) 5 else firstEmpty
            }
        }
        _uiState.update {
            it.copy(
                code = new,
                focusIndex = next,
                isValid = null,
                errorMessage = null
            )
        }

        updateButtonState()
    }

    private fun handleBackspace() {
        val current = _uiState.value.focusIndex.coerceIn(0, 5)
        val previous = (current - 1).coerceAtLeast(0)
        val new = _uiState.value.code.toMutableList()

        if (new[current] != null) {
            new[current] = null
            _uiState.update { it.copy(code = new, focusIndex = current, isValid = null, errorMessage = null) }
        } else {
            new[previous] = null
            _uiState.update { it.copy(code = new, focusIndex = previous, isValid = null, errorMessage = null) }
        }

        updateButtonState()
    }

    private fun handlePaste(pasteCode: List<Int?>) {
        val cleaned = pasteCode.mapNotNull { it }.take(6)
        if (cleaned.isEmpty()) return

        val newCode = List(6) { cleaned.getOrNull(it) }
        val lastIndex = (cleaned.size - 1).coerceAtMost(5)

        _uiState.update {
            it.copy(
                code = newCode,
                focusIndex = lastIndex,
                isValid = null,
                errorMessage = null
            )
        }

        updateButtonState()
    }

    fun verifyOtp(userId: String, verificationType: String) {
        val code = _uiState.value.code.joinToString("") { it?.toString() ?: "" }

        if (code.length < 6) {
            _uiState.update { it.copy(errorMessage = "Incomplete OTP", isValid = false) }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null, isSuccess = false, isValid = null) }

        viewModelScope.launch {
            val result = verifyOtpUseCase(
                userid = userId,
                verificationType = verificationType,
                otpCode = code
            )

            if (result.isSuccess) {
                val dto = result.getOrThrow()
                saveTokenUseCase(
                    accessToken = dto.accessToken,
                    refreshToken = dto.refreshToken,
                    refreshTokenExpiredAt = dto.refreshTokenExpiredAt,
                    userRole = dto.role
                ).collect { 
                    saveUserIdUseCase(userId).collect { 
                        applySuccess(dto)
                    }
                }
            } else {
                val e = result.exceptionOrNull()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        isValid = false,
                        errorMessage = e?.message ?: "OTP verification failed"
                    )
                }
            }
        }
    }

    private fun applySuccess(dto: VerifyOtpResult) {
        _uiState.update {
            it.copy(
                isLoading = false,
                isSuccess = true,
                isValid = true,
                accessToken = dto.accessToken,
                refreshToken = dto.refreshToken,
                role = dto.role,
                errorMessage = null
            )
        }
    }

    fun resendOtp(userId: String, verificationType: String, durationSeconds: Int = 60) {
        // Prevent multiple clicks if countdown is already running
        if (!_uiState.value.isResendEnabled && _uiState.value.resendRemainingSeconds > 0) return

        resendJob?.cancel()

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val result = resendOtpUseCase(userId = userId, verificationType = verificationType)

                if (result.isSuccess) {
                    // Start countdown on success
                    _uiState.update { it.copy(isResendEnabled = false, resendRemainingSeconds = durationSeconds) }
                    resendJob = viewModelScope.launch {
                        var remaining = durationSeconds
                        while (remaining > 0) {
                            delay(1000)
                            remaining -= 1
                            _uiState.update { it.copy(resendRemainingSeconds = remaining) }
                        }
                        _uiState.update { it.copy(isResendEnabled = true, resendRemainingSeconds = 0) }
                    }
                } else {
                    val e = result.exceptionOrNull()
                    _uiState.update { it.copy(errorMessage = e?.message ?: "Failed to resend OTP.") }
                }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun updateButtonState() {
        val filled = _uiState.value.code.all { it != null }
        _uiState.update { it.copy(isButtonEnabled = filled) }
    }

    override fun onCleared() {
        super.onCleared()
        resendJob?.cancel()
    }
}
