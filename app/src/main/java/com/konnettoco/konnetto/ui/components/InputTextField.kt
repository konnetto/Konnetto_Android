package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    input: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    trailingIcon: Painter? = null,
    customTrailingIcon: (@Composable () -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isAvailable: Boolean? = null,
) {
    val colors = when (isAvailable) {
        true -> OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.Green,
            focusedLabelColor = Color.Green,
            cursorColor = Color.Green
        )
        false -> OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.error,
            unfocusedBorderColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = MaterialTheme.colorScheme.error,
            cursorColor = MaterialTheme.colorScheme.error
        )
        null -> OutlinedTextFieldDefaults.colors()
    }

    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
        value = input,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        trailingIcon = {
            when {
                customTrailingIcon != null -> customTrailingIcon.invoke()
                trailingIcon != null -> Icon(
                    painter = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.clickable { onTrailingIconClick?.invoke() }
                )
                else -> {}
            }
        },
        singleLine = true,
        keyboardOptions =  KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(12.dp),
        colors = colors
    )
}

@Preview(showBackground = true)
@Composable
private fun InputTextFieldPreview() {
    KonnettoTheme {
        InputTextField(
            input = "",
            onValueChange = {},
            labelText = "Email",
            keyboardType = KeyboardType.Text
        )
    }
}
