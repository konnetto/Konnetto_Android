package com.konnettoco.konnetto.ui.screen.profile.shareprofile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun QuickShareSection(
    modifier: Modifier = Modifier,
    onXShareClick: () -> Unit,
    onInstagramShareClick: () -> Unit,
    onWhatsAppShareClick: () -> Unit,
    onMoreClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Quick Share",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = onXShareClick,
                        modifier = Modifier.size(50.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            Color.Black
                        )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.x_logoicon),
                            contentDescription = "share to X",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "X",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(Modifier.width(40.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = onInstagramShareClick,
                        modifier = Modifier.size(50.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            Color.Magenta
                        )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.instagram_logon),
                            contentDescription = "share to Instagram",
                            modifier = Modifier.size(24.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "Instagram",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(Modifier.width(40.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = onWhatsAppShareClick,
                        modifier = Modifier.size(50.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            Color.Green
                        )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.whatsapp_icon),
                            contentDescription = "share to Whatsapp",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "Whatsapp",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(Modifier.width(40.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = onMoreClick,
                        modifier = Modifier.size(50.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            Color.Gray
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_more_horiz_24),
                            contentDescription = "more",
                            tint = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "More",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuickShareSectionPrev() {
    KonnettoTheme {
        QuickShareSection(
            onXShareClick = {},
            onInstagramShareClick = {},
            onWhatsAppShareClick = {},
            onMoreClick = {}
        )
    }
}