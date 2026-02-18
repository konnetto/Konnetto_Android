package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme


@Composable
fun EventCard(
    eventBackgroundImage: String? = null,
    locationEvent: String,
    categoryEvent: String,
    nameEvent: String,
    dateEvent: String,
    timeEvent: String,
    totalInterests: Int,
    avatars: List<String>? = null,
    onClick: () -> Unit
) {

    val backgroundImage = rememberAsyncImagePainter(model = eventBackgroundImage)
    var categoryEventText by remember { mutableStateOf(categoryEvent) }
    var locationEventText by remember { mutableStateOf(locationEvent) }
    var totalInterestsInt by remember { mutableStateOf(totalInterests) }
    var nameEventText by remember { mutableStateOf(nameEvent) }
    var dateEventText by remember { mutableStateOf(dateEvent) }
    var timeEventText by remember { mutableStateOf(timeEvent) }

    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.width(365.dp).height(346.dp),
    ) {
        Box(
            modifier = Modifier.height(166.dp)
        ) {
            Image(
                painter = backgroundImage,
                modifier = Modifier.fillMaxWidth().height(160.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.4f),
                                Color.White
                            ),
                            startY = 250f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            Row(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(24.dp)
                        .width(115.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.58f),
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Text(
                        text = categoryEventText,
                        color = Color.White,
                        fontSize = 12.sp,
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {

            // Date Time
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .offset(y = (-25).dp)
                    .width(208.dp)
                    .height(36.dp)
                    .padding(end = 2.dp)
                    .background(Color(0xFF0B1324).copy(alpha = 0.67f), RoundedCornerShape(5.dp)),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.calendar_icon),
                        contentDescription = "calendar",
                        tint = Color.Green,
                        modifier = Modifier.height(24.dp).width(24.dp)
                    )
                    Text(
                        text = dateEventText,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 2.dp)
                    )

                    VerticalDivider(
                        modifier = Modifier.height(22.dp).padding(horizontal = 7.dp),
                        thickness = 2.dp,
                        color = Color.LightGray
                    )

                    Icon(
                        painter = painterResource(R.drawable.clock_icon),
                        contentDescription = "clock",
                        tint = Color.White,
                        modifier = Modifier.height(24.dp).width(24.dp)
                    )
                    Text(
                        text = timeEventText,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 2.dp, end = 4.dp)
                    )
                }
            }

            Text(nameEventText, fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Row() {
                Icon(Icons.Default.LocationOn, contentDescription = null)
                Text(locationEventText)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    AvatarStackFromUrl(imageUrls = avatars)
                    Text(
                        text = "$totalInterestsInt",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00C853),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "tertarik",
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }

                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "Lihat Detail",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
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
        EventCard(
            onClick = {},
            categoryEvent = "CONVETION",
            eventBackgroundImage = "https://ik.imagekit.io/6v306xm58/Rectangle%2012.png",
            locationEvent = "ICE BSD City, Tangerang",
            totalInterests = 3260,
            nameEvent = "Comifuro 19",
            dateEvent = "Sen, 12 Mei",
            timeEvent = "09:00 AM",
            avatars = listOf(
                "https://i.pravatar.cc/150?img=1",
                "https://i.pravatar.cc/150?img=2",
                "https://i.pravatar.cc/150?img=3",
            )
        )
    }
}