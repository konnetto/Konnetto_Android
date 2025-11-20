package com.konnettoco.konnetto.ui.screen.auth.otppages

interface OtpAction {
    data class OnEnterNumber(val number: Int?, val index: Int): OtpAction
    data class OnChangeFieldFocused(val index: Int): OtpAction
    data object OnKeyboardBack: OtpAction
    data class OnPaste(val code: List<Int?>): OtpAction
}