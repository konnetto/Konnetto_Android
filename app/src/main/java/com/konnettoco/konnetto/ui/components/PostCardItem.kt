package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import com.konnettoco.konnetto.utils.formatCount
import com.konnettoco.konnetto.utils.formatDateTime

@Composable
fun PostCardItem(
    modifier: Modifier = Modifier,
    displayname: String,
    username: String,
    createdAt: String,
    profilePict: String? = null,
    image: List<String>? = null,
    isFriend: Boolean,
    caption: String,
    totalLike: Int,
    totalComment: Int,
    totalShare: Int,
    isLiked: Boolean,
    isSaved: Boolean,
    showAddFriendButton: Boolean,
    onDisplaynameClick: () -> Unit,
    onPostClick: () -> Unit,
    onLikedCountClick: () -> Unit,
    onCommentsClick: () -> Unit,
    onAddFriendClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isLiked by remember { mutableStateOf(isLiked) }
    var isSaved by remember { mutableStateOf(isSaved) }
    var likeCount by remember { mutableIntStateOf(totalLike) }
    var commentCount by remember { mutableIntStateOf(totalComment) }
    var shareCount by remember { mutableIntStateOf(totalShare) }

    val avatarPainter = rememberAsyncImagePainter(model = profilePict)

    Column(
        modifier = modifier
            .fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(4.dp),
//        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onPostClick()
                }
                .padding(4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
            ) {
                Image(
                    painter = avatarPainter,
                    contentDescription = "profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(40.dp))
                        .background(
                            color = Color.LightGray
                        )
                )
            }
            Spacer(Modifier.widthIn(min = 8.dp))
            Column {
                Text(
                    text = displayname,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onDisplaynameClick()
                    }
                )
                Row {
                    Text(
                        text = username,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { }
                    )
                    Spacer(modifier = Modifier.widthIn(12.dp))
                    Text(
                        text = formatDateTime(createdAt),
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
        if (image != null && image.isNotEmpty()) {
            PostImageSlider(
                images = image,
            )
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
                    painter = if (isSaved) painterResource(R.drawable.baseline_bookmark_24) else painterResource(R.drawable.baseline_bookmark_border_24),
                    contentDescription = null,
                )
            }
        }
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostImageSlider(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    val limitedImages = remember(images) { images.take(10) } //maksimal 10 gambar
    val pagerState = rememberPagerState(pageCount = { limitedImages.size })

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) { page ->
                val painter = rememberAsyncImagePainter(model = limitedImages[page])
                Box(
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
                            ),
                    )
                    if (painter.state is AsyncImagePainter.State.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(70.dp),
                            color = Color.Gray
                        )
                    }
                }
            }
            // Nomor urut di pojok kanan atas
            if (limitedImages.size > 1) {
                Text(
                    text = "${pagerState.currentPage + 1}/${limitedImages.size}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        if (limitedImages.size > 1) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                repeat(limitedImages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(if (isSelected) 8.dp else 6.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) MaterialTheme.colorScheme.onSurface else Color.LightGray)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PostCardItemPreview() {
    KonnettoTheme {
        PostCardItem(
            displayname = "Char",
            username = "charaznable123",
            createdAt = "2025-08-24T07:15:32Z",
            profilePict = "R.drawable.logo",
            image = listOf(

            ),
            caption = "awok awok awoka aoak asdasd dfsdfa asda asdasd asdasda asdasda asdasdasd dfsdfsd sdfsdfsf sdfsdfs asku dain daska",
            onCommentsClick = {},
            totalLike = 0,
            totalComment = 0,
            totalShare = 0,
            isLiked = false,
            isSaved = true,
            onLikedCountClick = {},
            onDisplaynameClick = {},
            onPostClick = {},
            onAddFriendClick = {},
            isFriend = false,
            showAddFriendButton = true,
        )
    }
}