package com.konnettoco.konnetto.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konnettoco.konnetto.ui.theme.Green40
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun MyTextField(
    icon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    isAvailable: Boolean? = null,
    isPassword: Boolean
    ) {

    var passwordVisible by remember { mutableStateOf(false) }
    var visualTransformation: VisualTransformation

    if (isPassword) {
        visualTransformation = PasswordVisualTransformation()
    } else {
        visualTransformation = VisualTransformation.None
    }

    val placeholderContent: @Composable (() -> Unit)? = if (placeholder.isNotEmpty()) {
        { Text(text = placeholder, color = Color.LightGray) }
    } else {
        null
    }

    val trailingIconContent: @Composable (() -> Unit)? = if (isPassword) {
        {
            val image = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = image,
                    contentDescription = if (passwordVisible) "Sembunyikan password" else "Tampilkan password"
                )
            }
        }
    } else {
        null
    }

    val supportingTextContent: @Composable (() -> Unit)? = if (isPassword) {
        {
            Text(
                text = "Lupa kata sandi?",
                color = Color.Black,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                // .clickable { /* TODO: Tambahkan aksi navigasi ke halaman reset password */ }
            )
        }
    } else {
        null
    }

    val colorFormatted = when (isAvailable) {
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



    Column() {

        Row(
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Text(
                text = label,
                color = Color.Black,
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholderContent,
            leadingIcon = {
                Icon(imageVector = icon, contentDescription = label)
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            visualTransformation = visualTransformation,
            colors = colorFormatted,
            trailingIcon = trailingIconContent,
            supportingText = supportingTextContent,
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Normal Text Field")
@Composable
private fun MyTextFieldDarkPreview() {
    KonnettoTheme {
        // State sementara khusus untuk kebutuhan Preview
        var text by remember { mutableStateOf("") }

        MyTextField(
            icon = Icons.Default.Person,
            value = text,
            onValueChange = { text = it },
            label = "Username",
            placeholder = "Masukkan email atau username",
            isPassword = false,
            isAvailable = true
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Password Text Field")
@Composable
private fun MyTextFieldPasswordPreview() {
    KonnettoTheme {
        var password by remember { mutableStateOf("rahasia123") }

        MyTextField(
            icon = Icons.Default.Lock,
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            isAvailable = true
        )
    }
}