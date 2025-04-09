package com.zulfadar.konnetto.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.components.InputTextField
import com.zulfadar.konnetto.ui.components.RegularButton
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onClickToLogin: () -> Unit,
    onClickToSignUp: () -> Unit,
) {
    LoginContent(
        email = "",
        password = "",
        onClickToLogin = onClickToLogin,
        onClickToSignUp = onClickToSignUp,
        modifier = modifier
    )
}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onClickToLogin: () -> Unit,
    onClickToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(false) }
    val icon = if (visible) {
        painterResource(id = R.drawable.baseline_visibility_24)
    } else {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }

    var email by remember { mutableStateOf(email) }
    var password by remember { mutableStateOf(password) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(40.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(30.dp)
                .size(70.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Log in",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(Modifier.heightIn(min = 30.dp))
        InputTextField(
            input = email,
            onValueChange = { newInput ->
                email = newInput
            },
            labelText = "Email",
            leadingIcon = Icons.Filled.Email,
            keyboardType = KeyboardType.Email
        )
        Spacer(Modifier.heightIn(min = 10.dp))
        InputTextField(
            input = password,
            onValueChange = { newInput ->
                password = newInput.filterNot { it.isWhitespace() } // Mencegah input spasi
            },
            labelText = "Password",
            leadingIcon = Icons.Filled.Lock,
            trailingIcon = icon,
            onTrailingIconClick = { visible = !visible},
            keyboardType = KeyboardType.Password,
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(Modifier.heightIn(min = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), // Memastikan Row mengisi lebar penuh
            horizontalArrangement = Arrangement.Start // Menempatkan teks di kanan
        ) {
            Text(
                text = "Forgot Password?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable { }// Memberikan sedikit padding dari tepi kanan
            )
        }
        Spacer(Modifier.heightIn(min = 30.dp))
        RegularButton(
            text = "Continue",
            onClick = onClickToLogin,
        )
        Spacer(Modifier.heightIn(min = 70.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have acccount?",
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = "Sign up now",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        onClickToSignUp()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    KonnettoTheme {
        LoginContent(
            email = "",
            password = "",
            onClickToLogin = {

            },
            onClickToSignUp = {}
        )
    }
}