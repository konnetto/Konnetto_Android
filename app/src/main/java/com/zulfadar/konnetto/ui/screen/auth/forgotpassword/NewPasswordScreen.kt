package com.zulfadar.konnetto.ui.screen.auth.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.components.InputTextField
import com.zulfadar.konnetto.ui.components.RegularButton
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun NewPassWordScreen(
    modifier: Modifier = Modifier,
    onSendClick: () -> Unit,
) {
    NewPasswordContent(
        modifier = modifier,
        onSendClick = onSendClick,
        password = "",
        confirmPassword = ""
    )
}

@Composable
fun NewPasswordContent(
    modifier: Modifier = Modifier,
    password: String,
    confirmPassword: String,
    onSendClick: () -> Unit
) {
    var password by remember { mutableStateOf(password) }
    var confirmPassword by remember { mutableStateOf(confirmPassword) }

    Scaffold(
        modifier = modifier,
        topBar = {
            NewPasswordTopBar()
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))
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
                text = "Enter your new password!",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(Modifier.heightIn(min = 10.dp))
            InputTextField(
                input = password,
                onValueChange = { newInput ->
                    password = newInput
                },
                labelText = "Password",
                leadingIcon = Icons.Filled.Lock,
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
                leadingIcon = Icons.Filled.Lock,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.heightIn(min = 20.dp))
            RegularButton(
                text = "Send",
                onClick = onSendClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "New Password",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
    )
}

@Preview
@Composable
private fun NewPasswordPreview() {
    KonnettoTheme {
        NewPasswordContent(
            onSendClick = {},
            password = "",
            confirmPassword = ""
        )
    }
}