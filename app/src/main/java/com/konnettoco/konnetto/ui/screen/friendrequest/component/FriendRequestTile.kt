package com.konnettoco.konnetto.ui.screen.friendrequest.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R

@Composable
fun FriendRequestTile(
    modifier: Modifier = Modifier,
    displayname: String,
    username: String,
    profilePict: Int,
    timeStamp: Int,
    onAccaptClick: () -> Unit,
    onDeclineClick: () -> Unit,
    onDisplaynameClick: () -> Unit
) {
    Card(
        modifier = modifier
        .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.elevatedCardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                painter = painterResource(profilePict),
                contentDescription = "profile picture"
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .clickable { onDisplaynameClick },
                    text = displayname,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row {
                    Text(
                        text = "@$username",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$timeStamp h",
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .padding(2.dp)
                            .height(40.dp)
                            .width(130.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.surfaceContainerLowest
                        ),
                        shape = RoundedCornerShape(12.dp),
                        enabled = true,
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = "Decline",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(2.dp)
                            .height(40.dp)
                            .width(130.dp),
                        onClick = onAccaptClick,
                        colors = ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        enabled = true
                    ) {
                        Text(
                            text = "Accept",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendRequestTilePreview() {
    FriendRequestTile(
        displayname = "Fufufafa",
        username = "fufufafa",
        profilePict = R.drawable.image_mascot2,
        timeStamp = 12,
        onAccaptClick = {},
        onDeclineClick = {},
        onDisplaynameClick = {}
    )
}