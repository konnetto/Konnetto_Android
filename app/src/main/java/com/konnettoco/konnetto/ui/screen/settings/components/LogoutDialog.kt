package com.konnettoco.konnetto.ui.screen.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun LogoutDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    imageDescription: String,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // Ini adalah kuncinya
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .width(420.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(120.dp)
                )
                Text(
                    text ="Confirm Logout",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(3.dp),
                )
                Text(
                    text = "Are you sure you want to log out? \uD83E\uDD7A",
                    modifier = Modifier.padding(bottom = 16.dp, top = 2.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.size(width = 160.dp, height = 40.dp),
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFD9D9D9)),
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Button(
                        onClick = { onConfirmation() },
                        modifier = Modifier.size(width = 160.dp, height = 40.dp),
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    ) {
                        Text(
                            text = "Confirm",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true,)
@Composable
private fun LogoutDialogPrev() {
    KonnettoTheme {
        LogoutDialog(
            onDismissRequest = {},
            onConfirmation = {},
            painter = painterResource(R.drawable.image_mascot2),
            imageDescription = ""
        )
    }
}