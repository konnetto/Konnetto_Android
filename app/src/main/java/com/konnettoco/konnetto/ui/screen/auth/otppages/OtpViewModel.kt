package com.konnettoco.konnetto.ui.screen.auth.otppages

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//dummy otp
private const val VALID_OTP_CODE = "956789"

class OtpViewModel(

): ViewModel() {
    private val _uiState = MutableStateFlow(OTPState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: OtpAction) {
        when (action) {
            is OtpAction.OnChangeFieldFocused -> {
                _uiState.update { it.copy(
                    focusIndex = action.index
                ) }
            }
            is OtpAction.OnEnterNumber ->  {
                enterNumber(action.number, action.index)
            }
            OtpAction.OnKeyboardBack -> {
                val previousIndex = getPreviousFocusedIndex(uiState.value.focusIndex)
                _uiState.update { it.copy(
                    code = it.code.mapIndexed { index, number ->
                        if (index == previousIndex) {
                            null
                        } else {
                            number
                        }
                    },
                    focusIndex = previousIndex
                ) }
            }
            is OtpAction.OnPaste -> handlePaste(action.code)
        }
    }

    private fun handlePaste(pasteCode: List<Int?>) {
        val newCode = pasteCode.take(6) + List(6 - pasteCode.size) { null }
        _uiState.update {
            it.copy(
                code = newCode,
                focusIndex = pasteCode.size.coerceAtMost(5),
                isValid = if (newCode.none { n -> n == null }) {
                    newCode.joinToString("") == VALID_OTP_CODE
                } else null
            )
        }
    }

    private fun getPreviousFocusedIndex(currentIndex: Int?): Int? {
        return currentIndex?.minus(1)?.coerceAtLeast(0)
    }

    private fun getFirstEmptyIndexAfterFocusIndex(code: List<Int?>, currentFocusedIndex: Int): Int {
        code.forEachIndexed { index, number ->
            if (index <= currentFocusedIndex) {
                return@forEachIndexed
            }
            if (number == null) {
                return index
            }
        }
        return currentFocusedIndex
    }

    private fun getNextFocusedTextFieldIndex(currentCode: List<Int?>, currentFocusedIndex: Int?): Int? {
        if (currentFocusedIndex == null) {
            return null
        }
        if (currentFocusedIndex == 5) {
            return currentFocusedIndex
        }
        return getFirstEmptyIndexAfterFocusIndex(
            code = currentCode,
            currentFocusedIndex = currentFocusedIndex
        )
    }

    private fun enterNumber(number: Int?, index: Int)  {
        val newCode = uiState.value.code.mapIndexed { currentIndex, currentNumber ->
            if (index == currentIndex) {
                number
            } else {
                currentNumber
            }
        }
        val wasNumberRemove = number == null
        _uiState.update { it.copy(
            code = newCode,
            focusIndex = if (wasNumberRemove || it.code.getOrNull(index) != null) {
                it.focusIndex
            } else {
                getNextFocusedTextFieldIndex(
                    currentCode = it.code,
                    currentFocusedIndex = it.focusIndex
                )
            },
            isValid = if (newCode.none { it == null }) {
                newCode.joinToString { "" } == VALID_OTP_CODE
            } else null
        ) }
    }
}