package com.konnettoco.konnetto.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.konnettoco.konnetto.R

import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun CommunityCard(
    communityCategory: String,
    communityName: String,
    communityDesc: String,
    communityTopic: String,
    imgUrl: String,
    action: () -> Unit
) {
    var communityCategoryText by remember { mutableStateOf(communityCategory) }
    var communityNameText by remember { mutableStateOf(communityName) }
    var communityDescText by remember {  mutableStateOf(communityDesc) }
    var communityTopicText by remember { mutableStateOf(communityTopic) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier.size(width = 365.dp, height = 190.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
//            Top
            Row() {
//                Img
                AsyncImage(
                    model = imgUrl,
                    contentDescription = "Image Thumbnail",
                    modifier = Modifier.size(50.dp).background(color = Color.LightGray, shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Fit,
                )
//                Badge & Event Name
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().height(50.dp).padding(start = 16.dp)
                ) {
//                    Badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            color = Color(0xFFDDDDDD),
                            shape = RoundedCornerShape(4.dp))
                            .width(58.dp)
                            .height(24.dp).border(width = 1.dp, color = Color(0XFFA1A1A1), shape = RoundedCornerShape(4.dp)),
                    ) {
                        Text(
                            text = communityCategoryText,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0XFFA1A1A1)
                        )
                    }

//                    Event Name
                    Text(
                        text = communityNameText,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                }
            }

//            Middle
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                VerticalDivider(
                    modifier = Modifier.height(34.dp),
                    thickness = 2.dp,
                    color = Color(0xFFA1A1A1)
                )

                Row(
                    modifier = Modifier.width(291.dp).padding(start = 16.dp)
                ) {
                    Text(
                        text = "\"$communityDescText\"",
                        fontSize = 14.sp,
                        color = Color(0xFF5D5D5D),
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Italic,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            lineHeight = 16.sp
                        )
                    )
                }
            }

//            Bottom
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier.width(308.dp),
                    thickness = 1.dp,
                    color = Color(0xFFA1A1A1)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(top = 6.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.discussion_icon),
                        contentDescription = "discussion",
                        modifier = Modifier.size(30.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Lagi Bahas:",
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp),
                    )

                    Text(
                        text = communityTopicText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CommunityCardPreview() {
    KonnettoTheme {
        CommunityCard(
            communityCategory = "Event",
            communityName = "ANIME JAKARTA",
            communityDesc = "Diskusi santai seputar event jejepangan di jabodetabek.",
            communityTopic = "Persiapan Comifuro 19",
            imgUrl = "https://ik.imagekit.io/6v306xm58/Rectangle%2012.png",
            action = {},
        )
    }
}