package com.konnettoco.konnetto.ui.screen.discovery.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import com.konnettoco.konnetto.utils.getGenreColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DiscoveryCardItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    year: Int,
    status: String,
    isAlreadyAdded: Boolean,
    genres: List<String>
) {
    val painter = rememberAsyncImagePainter(model = image)
    val painterState = painter.state

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .width(250.dp)
            .clickable {  },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surfaceContainerLowest
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .height(240.dp)
                        .fillMaxWidth()
                )
                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center),
                        color = Color.Gray
                    )
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.TopEnd).padding(12.dp),
                    colors = if (isAlreadyAdded == true ) IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.White) else IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest, contentColor = MaterialTheme.colorScheme.onSurface)
                ) {
                    Icon(
                        imageVector = if (isAlreadyAdded == true ) Icons.Default.Check else Icons.Default.Add,
                        contentDescription = null,
                        tint = if (isAlreadyAdded == true) Color.White else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = title,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .width(80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = year.toString(),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = " • ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = status,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            FlowRow(
                modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                genres.forEach { genre ->
                    val (bgColor, textColor) = getGenreColor(genre)
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = textColor,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .background(
                                color = bgColor,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(vertical = 2.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = genre,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor,
                        )
                    }
                    Spacer(Modifier.width(4.dp))
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiscoveryCardItemPrev() {
    KonnettoTheme {
        DiscoveryCardItem(
            image = "",
            title = "Mobile Suit Gundam",
            year = 2013,
            status = "On Going",
            isAlreadyAdded = false,
            genres = listOf("mecha", "action", "adventure")
        )
    }
}