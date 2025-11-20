package com.konnettoco.konnetto.ui.screen.auth.register

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.components.InputTextField
import com.konnettoco.konnetto.ui.components.RegularButton
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onClickToRegister: (userId: String, otpExpiredAt: String) -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerUiSate by viewModel.registerState.collectAsState()

    val context = LocalContext.current

    // Toast success
    LaunchedEffect(registerUiSate.isSuccess) {
        val userId = registerUiSate.userId
        val otpExpiredAt = registerUiSate.otpExpiredAt

        if (registerUiSate.isSuccess && userId != null && otpExpiredAt != null) {
            Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
            onClickToRegister(userId, otpExpiredAt)
            viewModel.resetSuccess()
        }
    }

    // Toast error
    LaunchedEffect(registerUiSate.error) {
        registerUiSate.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    RegisterContent(
        registerState = registerUiSate,
        name = registerUiSate.name,
        username = registerUiSate.username,
        email = registerUiSate.email,
        password = registerUiSate.password,
        confirmPassword = registerUiSate.confirmPassword,
        onNameChange = viewModel::onNameChange,
        onUsernameChange = viewModel::onUsernameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onRegisterClick = { viewModel.register() },
        navigateToLogin = navigateToLogin,
    )
}

@Composable
fun RegisterContent(
    name: String,
    username: String,
    email: String,
    password: String,
    registerState: RegisterState,
    confirmPassword: String,
    onNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Disable if all field are empty or error
    val isButtonEnabled = name.isNotBlank() &&
            username.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            confirmPassword.isNotBlank() &&
            !registerState.isLoading

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
                    painter = painterResource(R.drawable.newlogo_konnetto),
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
                input = name,
                onValueChange = { onNameChange(it) },
                labelText = "Name",
                keyboardType = KeyboardType.Text
            )
            registerState.errorName?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = username,
                onValueChange = { onUsernameChange(it) },
                labelText = "Username",
                keyboardType = KeyboardType.Text
            )
            registerState.errorUsername?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = email,
                onValueChange = { onEmailChange(it) },
                labelText = "Email",
                keyboardType = KeyboardType.Email
            )
            registerState.errorEmail?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = password,
                onValueChange = { onPasswordChange(it) },
                labelText = "Password",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            registerState.errorPassword?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = confirmPassword,
                onValueChange = { onConfirmPasswordChange(it) },
                labelText = "Password Confirmation",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            registerState.errorConfirmPassword?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(Modifier.heightIn(min = 30.dp))
            RegularButton(
                text = "Register",
                onClick = onRegisterClick,
                enable = isButtonEnabled,
                loading = registerState.isLoading,
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
            name = "",
            username = "",
            email = "",
            password = "",
            confirmPassword = "",
            onNameChange = {},
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onRegisterClick = {},
            navigateToLogin = {},
            registerState = RegisterState(),
        )
    }
}