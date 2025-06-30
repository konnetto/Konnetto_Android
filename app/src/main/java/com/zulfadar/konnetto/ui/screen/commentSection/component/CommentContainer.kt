package com.zulfadar.konnetto.ui.screen.commentSection.component

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.utils.formatCount

@Composable
fun CommentContainer(
    modifier: Modifier = Modifier,
    displayname: String,
    timeStamp: Int,
    comment: String,
    isLiked: Boolean = false,
    likeCount: Int = 0,
    replyCount: Int,
) {
    var isLiked by remember { mutableStateOf(isLiked) }
    var likeCount by remember { mutableIntStateOf(likeCount) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
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
                text = "${timeStamp} h",
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
            Spacer(modifier = Modifier.width(190.dp))
            if (likeCount > 0) {
                Box(
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .clickable {  }
                ) {
                    Text(
                        text = likeCount.formatCount(),
                        fontSize = 12.sp,
                        color = if (likeCount > 0) Color.Gray
                        else Color.Transparent
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier
                    .size(18.dp),
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

@Preview(showBackground = true)
@Composable
private fun CommentContainerPreview() {
    CommentContainer(
        displayname = "Bambank",
        timeStamp = 4,
        comment = "Halo selamat pagi dunia!! asdasd asdasdasda asdasd ergt hty tjj yjyjyu yjujyujy ujyujyu jyujyujy jyujyujyujyujy ujyuky ukyukyukyukyukyukyukyk ykyuky yukyukmofmowe cwef wef wefwefqwdjqjqs cnd ",
        isLiked = false,
        likeCount = 2000,
        replyCount = 130,
    )
}