package com.konnettoco.konnetto.ui.screen.auth.login

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.components.InputTextField
import com.konnettoco.konnetto.ui.components.RegularButton
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onClickToLogin: () -> Unit,
    onClickToSignUp: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(loginState.isSuccess) {
        if (loginState.isSuccess) {
            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
            onClickToLogin()
        }
    }

    LaunchedEffect(loginState.error) {
        loginState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    LoginContent(
        modifier = modifier,
        loginState = loginState,
        email = loginState.emailOrUsername,
        password = loginState.password,
        onEmailOrUsernameChange = viewModel::onEmailOrPasswordChange,
        onPasswordChange = viewModel::onPasswordChange,
        onClickToLogin = { viewModel.login() },
        onClickToSignUp = onClickToSignUp,
        onForgotPasswordClick = onForgotPasswordClick
    )
}

@Composable
fun LoginContent(
    loginState: LoginState,
    email: String,
    password: String,
    onEmailOrUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickToLogin: () -> Unit,
    onClickToSignUp: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isButtonEnabled = email.isNotBlank() && password.isNotBlank()
    var visible by remember { mutableStateOf(false) }
    val icon = if (visible) {
        painterResource(id = R.drawable.baseline_visibility_24)
    } else {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }

    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(60.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
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
                text = "Welcome back!",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Sign in to continue",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(Modifier.heightIn(min = 30.dp))
            InputTextField(
                input = email,
                onValueChange = { onEmailOrUsernameChange(it) },
                labelText = "Email",
                keyboardType = KeyboardType.Email
            )
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = password,
                onValueChange = { onPasswordChange(it) },
                labelText = "Password",
                trailingIcon = icon,
                onTrailingIconClick = { visible = !visible},
                keyboardType = KeyboardType.Password,
                visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation()
            )
            loginState.errorPassword?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.heightIn(min = 20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {
                            onForgotPasswordClick()
                        }
                )
            }
            Spacer(Modifier.heightIn(min = 30.dp))
            RegularButton(
                text = "Sign In", // Changed text for clarity
                onClick = onClickToLogin,
                enable = isButtonEnabled,
                loading = loginState.isLoading
            )
            Spacer(Modifier.heightIn(min = 30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an acccount?",
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
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
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    KonnettoTheme {
        LoginContent(
            email = "",
            password = "",
            onClickToLogin = {},
            onClickToSignUp = {},
            onForgotPasswordClick = {},
            loginState = LoginState(),
            onEmailOrUsernameChange = {},
            onPasswordChange = {},
        )
    }
}