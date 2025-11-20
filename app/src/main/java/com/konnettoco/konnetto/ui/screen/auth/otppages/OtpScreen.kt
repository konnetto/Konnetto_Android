package com.konnettoco.konnetto.ui.screen.auth.otppages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.components.RegularButton
import com.konnettoco.konnetto.ui.screen.auth.otppages.component.OTPInputField
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    userId: String,
    otpExpiredAt: String,
    onConfirmClick: () -> Unit,
    otpViewModel: OtpViewModel = viewModel(),
) {
    val otpState by otpViewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { List(6) { FocusRequester() } }
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    LaunchedEffect(otpState.focusIndex) {
        otpState.focusIndex?.let { index ->
            focusRequester.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(otpState.code, keyboardManager) {
        val allNumberEntered = otpState.code.none { it == null }
        if (allNumberEntered) {
            focusRequester.forEach { it.freeFocus() }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }

    LaunchedEffect(otpState.code, keyboardManager) {
        val allNumberEntered = otpState.code.none { it == null }
        if (allNumberEntered) {
            focusRequester.forEach {
                it.freeFocus()
            }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }
    OtpContent(
        modifier = modifier,
        state = otpState,
        onAction = { action ->
            if (action is OtpAction.OnEnterNumber && action.number != null) {
                focusRequester[action.index].freeFocus()
            }
            otpViewModel.onAction(action)
        },
        focusRequester = focusRequester,
        onConfirmClick = onConfirmClick
    )
}

@Composable
fun OtpContent(
    modifier: Modifier = Modifier,
    state: OTPState,
    onAction: (OtpAction) -> Unit,
    focusRequester: List<FocusRequester>,
    onConfirmClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0x8072DEA6)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_security_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(Modifier.height(40.dp))
        Text(
            text = "Verify Your Account",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "We sent a code to your email",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(Modifier.heightIn(min = 30.dp))
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            state.code.forEachIndexed { index, number ->
                OTPInputField(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f)
                        .aspectRatio(1f),
                    number = number,
                    focusRequester = focusRequester[index],
                    onFocusChange = { isFocused ->
                        if (isFocused) {
                            onAction(OtpAction.OnChangeFieldFocused(index))
                        }
                    },
                    onNumberChange = { newNumber ->
                        onAction(OtpAction.OnEnterNumber(newNumber, index))
                    },
                    onKeyboardBack = {
                        onAction(OtpAction.OnKeyboardBack)
                    },
                    onPaste = { pastedText ->
                        val digits = pastedText
                            .take(6)
                            .map { it.toString().toIntOrNull() }
                        onAction(OtpAction.OnPaste(digits))
                    }
                )

            }
        }
        state.isValid?.let { isValid ->
            Text(
                text = if (isValid) "OTP is valid!" else "OTP is invalid!",
                color = if (isValid) MaterialTheme.colorScheme.primary else Color.Red,
                fontSize = 16.sp
            )
        }
        Spacer(Modifier.heightIn(min = 10.dp))
        RegularButton(
            text = "Confirm",
            onClick = onConfirmClick,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Didn't receive the code?",
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = "Resend Code",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .clickable {

                    }
            )
        }
        Spacer(Modifier.heightIn(min = 30.dp))
    }
}



@Composable
fun OtpSuccessScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0x8072DEA6)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_security_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(Modifier.height(40.dp))
        Text(
            text = "Welcome to KONNETTO!",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "Your account has been verified successfully",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(Modifier.heightIn(min = 30.dp))
        RegularButton(
            text = "Get Started",
            onClick = {},
            modifier = Modifier.imePadding()
        )
        Spacer(Modifier.heightIn(min = 30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun OTPPagePrev() {
    KonnettoTheme {
        val state = OTPState() // default: 6 digit null
        val focusRequesters = List(state.code.size) { FocusRequester() }
        OtpContent(
            state = state,
            onAction = {},
            focusRequester = focusRequesters,
            onConfirmClick = {}
        )
    }
}
