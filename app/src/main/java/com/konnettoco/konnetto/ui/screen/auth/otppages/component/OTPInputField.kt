package com.konnettoco.konnetto.ui.screen.auth.otppages.component

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun OTPInputField(
    number: Int?,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    isFocused: Boolean,
    onFocusChange: (Boolean) -> Unit,
    onNumberChange: (Int?) -> Unit,
    onBack: () -> Unit,
    onPaste: (String) -> Unit,
) {
    BasicTextField(
        value = number?.toString() ?: "",
        onValueChange = { value ->
            when {
                // Paste multiple digits
                value.length > 1 && value.all { it.isDigit() } -> onPaste(value)

                // Single digit input
                value.length <= 1 && value.all { it.isDigit() } -> onNumberChange(value.toIntOrNull())

                // ignore invalid
                else -> Unit
            }
        },
        textStyle = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        modifier = modifier
            .size(50.dp)
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .focusRequester(focusRequester)
            .onFocusChanged { onFocusChange(it.isFocused) }
            .onKeyEvent { event ->
                if (event.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL && number == null) {
                    onBack()
                    true
                } else false
            }
    ) { inner ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(6.dp)
        ) {
            inner()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OTPInputFieldPrev() {
    KonnettoTheme {
        OTPInputField(
            modifier = Modifier.size(100.dp),
            number = null,
            focusRequester = remember { FocusRequester() },
            onFocusChange = {},
            onNumberChange = {},
            onBack = {},
            onPaste = {},
            isFocused =  false,
        )
    }
}