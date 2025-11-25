package com.konnettoco.konnetto.ui.screen.auth.otppages

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.components.RegularButton
import com.konnettoco.konnetto.ui.screen.auth.otppages.component.OTPInputField

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    userId: String,
    verificationType: String,
    onConfirmClick: () -> Unit,
    otpViewModel: OtpViewModel = hiltViewModel(),
) {
    val state by otpViewModel.uiState.collectAsStateWithLifecycle()
    val focusRequesters = remember { List(6) { FocusRequester() } }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            val msg = if (verificationType == "REGISTER") {
                "Registration Successful, Welcome to Konnetto"
            } else {
                "Login Successful, Welcome Back"
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            onConfirmClick()
        }
    }

    LaunchedEffect(state.focusIndex) {
        val idx = state.focusIndex.coerceIn(0, 5)
        focusRequesters.getOrNull(idx)?.requestFocus()
    }

    LaunchedEffect(state.code) {
        val allEntered = state.code.none { it == null }
        if (allEntered) {
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    }

    OtpContent(
        modifier = modifier,
        state = state,
        focusRequesters = focusRequesters,
        onAction = otpViewModel::onAction,
        onConfirmClick = { otpViewModel.verifyOtp(userId, verificationType) },
        onResendClick = { otpViewModel.resendOtp(userId, verificationType) }
    )
}

@Composable
fun OtpContent(
    modifier: Modifier = Modifier,
    state: OTPState,
    focusRequesters: List<FocusRequester>,
    onAction: (OtpAction) -> Unit,
    onConfirmClick: () -> Unit,
    onResendClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(60.dp))
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
                    number = number,
                    focusRequester = focusRequesters[index],
                    isFocused = state.focusIndex == index,
                    onFocusChange = { isFocused ->
                        if (isFocused) onAction(OtpAction.OnChangeFieldFocused(index))
                    },
                    onNumberChange = { num -> onAction(OtpAction.OnEnterNumber(num, index)) },
                    onBack = { onAction(OtpAction.OnKeyboardBack) },
                    onPaste = { str ->
                        val digits = str.filter { it.isDigit() }.take(6).map { it.toString().toIntOrNull() }
                        onAction(OtpAction.OnPaste(digits))
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f)
                        .aspectRatio(1f),
                )
            }
        }

        state.errorMessage?.let { msg ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        } ?: run {
            state.isValid?.let { valid ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (valid) "OTP is valid!" else "OTP is invalid!",
                    color = if (valid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }
        }
        Spacer(Modifier.heightIn(min = 10.dp))
        RegularButton(
            text = if (state.isLoading) "Verifying..." else "Confirm",
            onClick = onConfirmClick,
            enable =  state.isButtonEnabled && !state.isLoading,
            loading = state.isLoading,
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
            val resendText = when {
                state.isResendEnabled -> "Resend Code"
                else -> "Resend in ${state.resendRemainingSeconds}s"
            }
            Text(
                text = resendText,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .clickable(enabled = state.isResendEnabled) {
                        onResendClick()
                    }
            )
        }
        Spacer(Modifier.heightIn(min = 30.dp))
    }
}



//@Composable
//fun OtpSuccessScreen(
//    modifier: Modifier = Modifier
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxSize()
//    ) {
//        Spacer(Modifier.height(40.dp))
//        Box(
//            modifier = Modifier
//                .padding(8.dp)
//                .size(80.dp)
//                .clip(CircleShape)
//                .background(Color(0x8072DEA6)),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                painter = painterResource(R.drawable.baseline_security_24),
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(horizontal = 8.dp)
//                    .size(30.dp)
//                    .clip(CircleShape)
//            )
//        }
//        Spacer(Modifier.height(40.dp))
//        Text(
//            text = "Welcome to KONNETTO!",
//            fontWeight = FontWeight.Bold,
//            fontSize = 24.sp
//        )
//        Text(
//            text = "Your account has been verified successfully",
//            fontWeight = FontWeight.Normal,
//            fontSize = 14.sp,
//            color = Color.Gray
//        )
//        Spacer(Modifier.heightIn(min = 30.dp))
//        RegularButton(
//            text = "Get Started",
//            onClick = {},
//            modifier = Modifier.imePadding()
//        )
//        Spacer(Modifier.heightIn(min = 30.dp))
//    }
//}

//@Preview(showBackground = true)
//@Composable
//private fun OTPPagePrev() {
//    KonnettoTheme {
//        val state = OTPState() // default: 6 digit null
//        val focusRequesters = List(state.code.size) { FocusRequester() }
//        OtpContent(
//            state = state,
//            onAction = {},
//            focusRequester = focusRequesters,
//            onConfirmClick = {}
//        )
//    }
//}
