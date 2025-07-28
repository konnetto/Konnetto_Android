package com.konnettoco.konnetto.ui.screen.auth.register

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.components.InputTextField
import com.konnettoco.konnetto.ui.components.RegularButton
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onClickToRegister: () -> Unit,
    navigateToLogin: () -> Unit
) {
    RegisterContent(
        displayname = "",
        username = "",
        email = "",
        password = "",
        confirmPassword = "",
        onClickToRegister = onClickToRegister,
        navigateToLogin = navigateToLogin,
        modifier = modifier
    )
}

@Composable
fun RegisterContent(
    displayname: String,
    username: String,
    email: String,
    password: String,
    confirmPassword: String,
    onClickToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var displayname by remember { mutableStateOf(displayname) }
    var username by remember { mutableStateOf(username) }
    var email by remember { mutableStateOf(email) }
    var password by remember { mutableStateOf(password) }
    var confirmPassword by remember { mutableStateOf(confirmPassword) }

    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.konnetto_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(70.dp)
                        .clip(CircleShape)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "Konnetto",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "Create your account",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "Join the anime community",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(Modifier.heightIn(min = 30.dp))
            InputTextField(
                input = displayname,
                onValueChange = { newInput ->
                    displayname = newInput
                },
                labelText = "Name",
                keyboardType = KeyboardType.Text
            )
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = username,
                onValueChange = { newInput ->
                    username = newInput.filterNot { it.isWhitespace() }
                },
                labelText = "Username",
                keyboardType = KeyboardType.Text
            )
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = email,
                onValueChange = { newInput ->
                    email = newInput.filterNot { it.isWhitespace() }
                },
                labelText = "Email",
                keyboardType = KeyboardType.Email
            )
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = password,
                onValueChange = { newInput ->
                    password = newInput
                },
                labelText = "Password",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = confirmPassword,
                onValueChange = { newInput ->
                    confirmPassword = newInput
                },
                labelText = "Password Confirmation",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.heightIn(min = 30.dp))
            RegularButton(
                text = "Register",
                onClick = onClickToRegister,
                modifier = Modifier.imePadding()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have acccount?",
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Text(
                    text = "Log in now",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            navigateToLogin()
                        }
                )
            }
            Spacer(Modifier.heightIn(min = 30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    KonnettoTheme {
        RegisterContent(
            displayname = "",
            username = "",
            email = "",
            password = "",
            onClickToRegister = {},
            navigateToLogin = {},
            confirmPassword = "",
        )
    }
}