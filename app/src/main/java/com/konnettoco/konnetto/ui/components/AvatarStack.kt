package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun AvatarStackFromUrl(
    imageUrls: List<String>? = null,
    size: Dp = 24.dp,
    overlap: Dp = 10.dp,
    maxVisible: Int = 3
) {

    val visible = imageUrls?.take(maxVisible)
    val remaining = imageUrls?.size?.minus(maxVisible)

    Row(verticalAlignment = Alignment.CenterVertically) {

        Box(Modifier.padding(start = 16.dp)) {
            visible?.forEachIndexed { index, url ->
                NetworkAvatar(url, index, size, overlap)
            }

            if (remaining != null) {
                if (remaining > 0) {
                    Box(
                        modifier = Modifier
                            .size(size)
                            .offset(x = (-overlap * maxVisible))
                            .zIndex(0f)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .border(1.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+$remaining",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NetworkAvatar(
    url: String,
    index: Int,
    size: Dp,
    overlap: Dp
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size)
            .offset(x = (-overlap * index))
            .zIndex(100f - index)
            .clip(CircleShape)
            .background(Color.LightGray)
            .border(1.dp, Color.White, CircleShape)
//        placeholder = painterResource(R.drawable.avatar_placeholder),
//        error = painterResource(R.drawable.avatar_placeholder)
    )
}