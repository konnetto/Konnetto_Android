package com.konnettoco.konnetto.ui.components

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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import com.konnettoco.konnetto.utils.formatCount
import com.konnettoco.konnetto.utils.getGenreColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SugoiPicksCardItem(
    modifier: Modifier = Modifier,
    posterImage: String,
    title: String,
    rating: Double,
    releaseDate: String,
    genres: List<String>,
    displayname: String,
    username: String,
    timestamp: String,
    profilePict: Int,
    image: String? = null,
    caption: String,
    totalLike: Int,
    totalComment: Int,
    totalShare: Int,
    isLiked: Boolean,
    onLikedCountClick: () -> Unit,
    onCommentsClick: () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isLiked by remember { mutableStateOf(isLiked) }
    var isSaved by remember { mutableStateOf(false) }
    var likeCount by remember { mutableIntStateOf(totalLike) }
    var commentCount by remember { mutableIntStateOf(totalComment) }
    var shareCount by remember { mutableIntStateOf(totalShare) }

    val painter = rememberAsyncImagePainter(model = image)
    val posterPainter = rememberAsyncImagePainter(model = posterImage)
    val painterState = painter.state
    val posterPainterState = posterPainter.state

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),

    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            elevation = CardDefaults.cardElevation(2.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xB0C5FFD2),
                                Color(0xC3C7FFF6)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = posterPainter,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .background(
                                        color = Color.LightGray
                                    )
                                    .size(height = 150.dp, width = 100.dp)
                            )
                            if (posterPainterState is AsyncImagePainter.State.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color.Gray
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                            ) {
                                Text(
                                    modifier = Modifier.width(160.dp).clickable {  },
                                    text = title,
                                    fontSize = 16.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                                Row(
                                    modifier = Modifier.width(80.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.size(30.dp),
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = Color.Yellow
                                    )
                                    Text(
                                        text = rating.toString(),
                                        fontSize = 16.sp,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    )
                                }
                            }
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Release date: ${releaseDate}",
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            //Genres
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                genres.forEach { genre ->
                                    val (bgColor, textColor) = getGenreColor(genre)
                                    Box(
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = textColor, // atau gunakan warna lain
                                                shape = RoundedCornerShape(50)
                                            )
                                            .background(
                                                color = bgColor,
                                                shape = RoundedCornerShape(50)
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
                        }
                    }
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = "Add to Library",
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(profilePict),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(40.dp))
                )
            }
            Spacer(Modifier.widthIn(min = 8.dp))
            Column {
                Text(
                    text = displayname,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {  }
                )
                Row {
                    Text(
                        text = "@$username",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { }
                    )
                    Spacer(modifier = Modifier.widthIn(12.dp))
                    Text(
                        text = timestamp,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val displayedText = if (isExpanded || caption.length <= 100) caption else "${caption.take(100)}..."

            Text(
                text = displayedText,
                textAlign = TextAlign.Justify,
                lineHeight = 16.sp,
                fontSize = 14.sp
            )
            if (caption.length > 100) {
                Text(
                    text = if (isExpanded) "See Less" else "See More",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(top = 4.dp)
                        .align(Alignment.End)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        if (image != null) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = "image post",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(height = 450.dp, width = 388.dp)
                        .background(
                            color = Color.LightGray
                        )
                        .clip(RoundedCornerShape(16.dp)),
                )
                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(70.dp),
                        color = Color.Gray
                    )
                }
            }
        }
        Spacer(Modifier.height( 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = {
                        isLiked = !isLiked
                        likeCount = if (isLiked) likeCount + 1 else maxOf(likeCount - 1, 0)
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onSurface
                    )
                }
                if (likeCount > 0) {
                    Box(
                        modifier = Modifier
                            .widthIn(min = 20.dp)
                            .clickable {
                                onLikedCountClick()
                            }
                    ) {
                        Text(
                            text = likeCount.formatCount(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (likeCount > 0) MaterialTheme.colorScheme.onSurface
                            else Color.Transparent
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        onCommentsClick()
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(R.drawable.icons_comment),
                        contentDescription = null,
                    )
                }
                if (commentCount > 0) {
                    Box(modifier = Modifier.widthIn(min = 20.dp)) {
                        Text(
                            text = commentCount.formatCount(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (commentCount > 0) MaterialTheme.colorScheme.onSurface
                            else Color.Transparent
                        )
                    }
                }
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(R.drawable.share_icon_outlined) ,
                        contentDescription = null,
                    )
                }
                if (shareCount > 0) {
                    Box(modifier = Modifier.widthIn(min = 20.dp)) {
                        Text(
                            text = shareCount.formatCount(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (shareCount > 0) MaterialTheme.colorScheme.onSurface
                            else Color.Transparent
                        )
                    }
                }
            }
            IconButton(
                modifier = Modifier,
                onClick = { isSaved = !isSaved}
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = if (isSaved) painterResource(R.drawable.baseline_bookmark_24) else painterResource(
                        R.drawable.baseline_bookmark_border_24),
                    contentDescription = null,
                )
            }
        }
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}


@Preview(showBackground = true)
@Composable
private fun SugoiPicksCardItemPreview() {
    KonnettoTheme {
        SugoiPicksCardItem(
            posterImage = "",
            title = "Mobile Suit Gundam GQuuuuuuux",
            rating = 8.9,
            releaseDate = "summer 2025",
            genres = listOf(
                "Mecha",
                "Military",
                "Action",
                "romance",
                "drama"
            ),
            displayname = "Char",
            username = "charaznable123",
            timestamp = "16 h",
            profilePict = R.drawable.logo,
            image = "",
            caption = "awok awok awoka aoak asdasd dfsdfa asda asdasd asdasda asdasda asdasdasd dfsdfsd sdfsdfsf sdfsdfs asku dain daska",
            onCommentsClick = {},
            totalLike = 0,
            totalComment = 0,
            totalShare = 0,
            isLiked = false,
            onLikedCountClick = {},
        )
    }
}