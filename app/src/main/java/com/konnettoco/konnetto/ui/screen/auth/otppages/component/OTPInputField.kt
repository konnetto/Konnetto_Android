package com.konnettoco.konnetto.ui.screen.auth.otppages.component

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun OTPInputField(
    modifier: Modifier = Modifier,
    number: Int?,
    focusRequester: FocusRequester,
    onFocusChange: (Boolean) -> Unit,
    onNumberChange: (Int?) -> Unit,
    onKeyboardBack: () -> Unit,
    onPaste: (String) -> Unit
) {
    var text by remember(number) {
        mutableStateOf(
            TextFieldValue(
                text = number?.toString().orEmpty(),
                selection = TextRange(
                    index = if (number != null ) 1 else 0
                )
            )
        )
    }
    var isFocused by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colorScheme.surfaceContainerLowest),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            modifier = Modifier
                .padding(10.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChange(it.isFocused)
                }
                .onKeyEvent { event ->
                    val pressedDelete = event.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL
                    if (pressedDelete && number == null) {
                        onKeyboardBack()
                    }
                    false
                },
            decorationBox = { innerBox ->
                innerBox()
                if (!isFocused && number == null) {
                    Text(
                        text = "",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }
            },
            value = text,
            onValueChange = { newText ->
                val newNumber = newText.text
//                if (newNumber.length <= 1 && newNumber.isDigitsOnly()) {
//                    onNumberChange(newNumber.toIntOrNull())
//                }
                when {
                    newNumber.length > 1 -> {
                        onPaste(newNumber)
                    }
                    newNumber.isDigitsOnly() -> {
                        onNumberChange(newNumber.toIntOrNull())
                    }
                }
            },
            cursorBrush = SolidColor(
                MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onSurface
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
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
            onKeyboardBack = {},
            onPaste = {}
        )
    }
}