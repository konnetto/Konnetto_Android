package com.konnettoco.konnetto.ui.screen.profile.userprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun WatchCardItem(
    modifier: Modifier = Modifier,
    posterImage: Int,
    title: String
) {
    Column(
        modifier = modifier
            .clickable {  }
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(posterImage),
                contentDescription = "poster image",
                modifier = Modifier
                    .size(width = 120.dp, height = 150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
            )
        }
        RoundedLinearProgressIndicator(
            progress = 0.7f,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
                .height(8.dp)
                .width(120.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = Color.LightGray,
        )
    }
}

@Composable
fun RoundedLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = Color.LightGray,
    cornerRadius: Dp = 50.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(trackColor),
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(
            progress = progress,
            color = color,
            trackColor = Color.Transparent, // agar latar belakang tidak menimpa rounded track
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius)) // penting agar foreground juga rounded
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WatchCardItemPreview() {
    KonnettoTheme {
        WatchCardItem(
            posterImage = R.drawable.memespongebob,
            title = "Spongbob"
        )
    }
}