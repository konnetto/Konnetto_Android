package com.konnettoco.konnetto.ui.screen.profile.shareprofile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun ShareProfileCardSection(
    modifier: Modifier = Modifier,
    profilePict: String? = null,
    displayname: String,
    username: String,
    watchedCount: Int,
    friendsCount: Int,
    bio: String,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(12.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Share Profile Card",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(60.dp))
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF179A07),
                                    Color(0xFF28FF65),
                                    Color(0xFF00CDAE)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(12.dp),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(profilePict?.toInt() ?: R.drawable.logo),
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(80.dp)
                                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                                    .padding(3.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(Modifier.width(8.dp))
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = displayname,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(Modifier.width(12.dp))
                                Text(
                                    text = "@"+username,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                            }
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 12.dp, vertical = 18.dp),
                            text = bio,
                            fontSize = 16.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                modifier = Modifier,
                                text = friendsCount.toString(),
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                modifier = Modifier,
                                text = "Friends",
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )
                            Spacer(Modifier.width(12.dp))
                            Text(
                                modifier = Modifier,
                                text = watchedCount.toString(),
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Spacer(Modifier.width(12.dp))
                            Text(
                                modifier = Modifier,
                                text = "Anime Watched",
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.width(160.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Share Card",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.width(160.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Save Image",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(Modifier.height(30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShareProfileCardSectionPrev() {
    KonnettoTheme {
        ShareProfileCardSection(
            profilePict = R.drawable.logo.toString(),
            displayname = "Char Aznable",
            username = "charaznable",
            watchedCount = 12,
            friendsCount = 4,
            bio = "Ini deskripsi profile (bio)"
        )
    }
}