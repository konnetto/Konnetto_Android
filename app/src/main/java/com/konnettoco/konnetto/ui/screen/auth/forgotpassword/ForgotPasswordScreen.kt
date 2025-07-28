package com.konnettoco.konnetto.ui.screen.auth.forgotpassword

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.components.InputTextField
import com.konnettoco.konnetto.ui.components.RegularButton
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun ForgotPassWordScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSendClick: () -> Unit,
) {
    ForgotPasswordContent(
        email = "",
        onBackClick = onBackClick,
        onSendClick = onSendClick
    )
}

@Composable
fun ForgotPasswordContent(
    modifier: Modifier = Modifier,
    email: String,
    onBackClick: () -> Unit,
    onSendClick: () -> Unit
) {
    var email by remember { mutableStateOf(email) }
    
    Scaffold(
        modifier = modifier,
        topBar = {
            ForgotPasswordTopBar(
                onBackClick = onBackClick
            )
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
                text = "Enter your email address!",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(Modifier.heightIn(min = 30.dp))
            InputTextField(
                input = email,
                onValueChange = { newInput ->
                    email = newInput.filterNot { it.isWhitespace() }
                },
                labelText = "Email",
                keyboardType = KeyboardType.Email
            )
            Spacer(Modifier.heightIn(min = 30.dp))
            RegularButton(
                text = "Send",
                onClick = onSendClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back),
                    contentDescription = "back button",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Forgot Password",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
    )
}

@Preview
@Composable
private fun ForgotPasswordPreview() {
    KonnettoTheme {
        ForgotPasswordContent(
            email = "",
            onBackClick = {},
            onSendClick = {}
        )
    }
}