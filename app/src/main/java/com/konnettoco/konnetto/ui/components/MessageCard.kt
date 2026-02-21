package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun MessageCard(
    eventName: String?,
    eventTopic: String?,
    contentMessage: String?,
    lastOpened: String?,
    category: String?,
    imgUrl: String?,
    categoryType: Int?
) {
    val cardCategoryBackgroundColor: Color
    val cardCategoryOutlineColor: Color
    val textCategoryColor: Color
    val categoryTypeData = categoryType ?: 0

//    Temporary color templates, Will be decided later for the color templates
    when (categoryTypeData) {
        1 -> {
            cardCategoryBackgroundColor = Color(0xFFFFC300).copy(alpha = 0.38f)
            cardCategoryOutlineColor = Color(0xFFFF8000)
            textCategoryColor = Color(0xFFFF8000)
        }
        2 -> {
            cardCategoryBackgroundColor = Color(0xFF01FFEA).copy(alpha = 0.38f)
            cardCategoryOutlineColor = Color(0xFF06C755)
            textCategoryColor = Color(0xFF06C755)
        }
        3 -> {
            cardCategoryBackgroundColor = Color(0xFF006FFF).copy(alpha = 0.38f)
            cardCategoryOutlineColor = Color(0xFF0033FF)
            textCategoryColor = Color(0xFF0033FF)
        }
        else -> {
            cardCategoryBackgroundColor = Color(0xFFFFC300).copy(alpha = 0.38f)
            cardCategoryOutlineColor = Color(0xFFFF8000)
            textCategoryColor = Color(0xFFFF8000)
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier.size(width = 365.dp, height = 133.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxWidth().padding(16.dp)
        ) {
//            Top
            Row() {
                AsyncImage(
                    model = imgUrl,
                    contentDescription = "Image Thumbnail",
                    modifier = Modifier.size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    //                Desc
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Column(modifier = Modifier.width(111.dp)) {
                            Text(
                                text = eventName ?: "",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = lastOpened ?: "",
                                fontSize = 12.sp,
                                color = Color(0xFF5D5D5D),
                                modifier = Modifier.offset(y = (-8).dp)
                            )
                        }

//                Category Badge
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = cardCategoryBackgroundColor
                            ),
                            border = BorderStroke(1.dp, cardCategoryOutlineColor),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.width(104.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = category ?: "",
                                    fontSize = 12.sp,
                                    color = textCategoryColor,
                                )
                            }
                        }

                    }


                    Column(
                        modifier = Modifier.width(221.dp)
                    ) {
                        Text(
                            text = eventTopic ?: "",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = contentMessage ?: "",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageCardPreview() {
    KonnettoTheme {
        MessageCard(
            eventName = "ANIME JAKARTA",
            lastOpened = "2m lalu",
            eventTopic = "Persiapan Event Comifuro 19",
            contentMessage = "Ada yang tau jadwal shuttle bus ke event nya?",
            category = "Topik Aktif",
            categoryType = 1,
            imgUrl = "https://ik.imagekit.io/6v306xm58/Rectangle%2012.png"
        )
    }
}