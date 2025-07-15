package com.zulfadar.konnetto.ui.screen.auth.otppages

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.common.OTPState
import com.zulfadar.konnetto.ui.common.OtpAction
import com.zulfadar.konnetto.ui.components.RegularButton
import com.zulfadar.konnetto.ui.screen.auth.otppages.component.OTPInputField
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    state: OTPState,
    onAction: (OtpAction) -> Unit,
    onConfirmClick: () -> Unit,
    focusRequester: List<FocusRequester>,
) {
    OtpContent(
        modifier = modifier,
        state = state,
        onAction = onAction,
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
