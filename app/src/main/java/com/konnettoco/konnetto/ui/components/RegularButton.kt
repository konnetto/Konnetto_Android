package com.konnettoco.konnetto.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun RegularButton(
    text: String,
    enable: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enable,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        modifier = modifier
            .fillMaxWidth()
            .padding(18.dp)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegularButtonPreview() {
    KonnettoTheme {
        RegularButton(
            text = "Continue",
            onClick = {}
        )
    }
}

@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RegularButtonDarkPreview() {
    KonnettoTheme {
        RegularButton(
            text = "Continue",
            onClick = {}
        )
    }
}