package com.zulfadar.konnetto.ui.screen.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R

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
                color = Color.Green,
            )
        }
        LinearProgressIndicator(
            progress = 0.7f,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
                .height(8.dp)
                .width(120.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = Color.LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WatchCardItemPreview() {
    WatchCardItem(
        posterImage = R.drawable.memespongebob,
        title = "Spongbob"
    )
}