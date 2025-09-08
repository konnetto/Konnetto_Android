package com.konnettoco.konnetto.ui.screen.discovery.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun SugoiPicksRecomendationCardItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    review: String
) {
    val painter = rememberAsyncImagePainter(model = image)
    val painterState = painter.state

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .width(300.dp)
            .clickable {  },
        elevation = CardDefaults.elevatedCardElevation(1.dp),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surfaceContainerLowest
        ),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(12.dp)),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .height(200.dp)
                )
                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center),
                        color = Color.Gray
                    )
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
                text = title,
                fontSize = 15.sp,
                maxLines = 1,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
                text = review,
                fontSize = 15.sp,
                maxLines = 1,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.height(4.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Watchlist"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SugoiPicksRecomendationCardItemPrev() {
    KonnettoTheme {
        SugoiPicksRecomendationCardItem(
            image = "",
            title = "Mobile Suit Gundam Gquuuuuux",
            review = "Samngat Absolute Cinema"
        )
    }
}