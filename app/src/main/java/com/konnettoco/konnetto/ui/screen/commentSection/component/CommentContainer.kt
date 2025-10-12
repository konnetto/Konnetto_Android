package com.konnettoco.konnetto.ui.screen.commentSection.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.utils.commentLikeFormatCount
import com.konnettoco.konnetto.utils.formatDateTime

@Composable
fun CommentContainer(
    modifier: Modifier = Modifier,
    avatar: String,
    displayname: String,
    timeStamp: String,
    comment: String,
    isLiked: Boolean = false,
    likeCount: Int = 0,
) {
    var isLiked by remember { mutableStateOf(isLiked) }
    var likeCount by remember { mutableIntStateOf(likeCount) }
    val avatarPainter = rememberAsyncImagePainter(model = avatar)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = avatarPainter,
                contentDescription = "profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color.LightGray
                    )
            )
            Column(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, )
            ) {
                Text(
                    modifier = Modifier.clickable {},
                    text = displayname,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = comment,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start
                )

            }
        }
        Row(
            modifier = Modifier.padding(start = 50.dp, top = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = formatDateTime(timeStamp),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier.clickable {},
                text = "Reply",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
//            Spacer(modifier = Modifier.width(140.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp), // kasih jarak dari tepi kanan
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (likeCount > 0) {
                        Text(
                            text = likeCount.commentLikeFormatCount(),
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(end = 4.dp) // jarak angka ke icon
                        )
                    }
                    IconButton(
                        modifier = Modifier.size(18.dp),
                        onClick = {
                            isLiked = !isLiked
                            likeCount = if (isLiked) likeCount + 1 else maxOf(likeCount - 1, 0)
                        }
                    ) {
                        Icon(
                            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = if (isLiked) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentContainerPreview() {
    CommentContainer(
        displayname = "Bambank",
        timeStamp = "2025-08-24T10:32:45Z",
        comment = "Halo selamat pagi dunia!! asdasd asdasdasda asdasd ergt hty tjj yjyjyu yjujyujy ujyujyu jyujyujy jyujyujyujyujy ujyuky ukyukyukyukyukyukyukyk ykyuky yukyukmofmowe cwef wef wefwefqwdjqjqs cnd ",
        isLiked = false,
        likeCount = 20000,
        avatar = "",
    )
}