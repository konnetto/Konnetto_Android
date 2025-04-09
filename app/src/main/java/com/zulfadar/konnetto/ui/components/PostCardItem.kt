package com.zulfadar.konnetto.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun PostCardItem(
    username: String,
    timestamp: String,
    profilePict: Int,
    image: Int? = null,
    caption: String,
    onCommentsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isLiked by remember { mutableStateOf(false) }
    var likeCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(profilePict),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.widthIn(min = 8.dp))
                Column {
                    Text(
                        text = username,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {  }
                    )
                    Text(
                        text = timestamp,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
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
            Spacer(Modifier.heightIn(min =8.dp))
            if (image != null) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .size(height = 350.dp, width = 250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(Modifier.heightIn(min = 8.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { isLiked = !isLiked },
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        "0",
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.widthIn(min = 18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onCommentsClick()
                            },
                        painter = painterResource(R.drawable.outline_insert_comment_24),
                        contentDescription = null,
                    )
                    Text(
                        "0",
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.widthIn(min = 18.dp))
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {  },
                        painter = painterResource(R.drawable.baseline_share_24) ,
                        contentDescription = null,
                    )
                    Text(
                        "0",
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(Modifier.heightIn(min = 15.dp))
        }
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
private fun PostCardItemPreview() {
    KonnettoTheme {
        PostCardItem(
            username = "Char Aznable",
            timestamp = "16 h",
            profilePict = R.drawable.logo,
            image = R.drawable.memespongebob,
            caption = "awok awok awoka aoak asdasd dfsdfa asda asdasd asdasda asdasda asdasdasd dfsdfsd sdfsdfsf sdfsdfs asku dain daska",
            onCommentsClick = {},
        )
    }
}