package com.zulfadar.konnetto.ui.screen.notification.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zulfadar.konnetto.R

@Composable
fun NotificationTile(
    modifier: Modifier = Modifier,
    username: String,
    notificationTitle: String,
    notificationImageProfile: Int,
    notificationTimeStamp: String,
    notificationCategory: String,
    onTileClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {  onTileClick }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            Image(
                painter = painterResource(notificationImageProfile),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )
            val (iconPainter, backgroundColor) = when (notificationCategory) {
                "like", "likedComment" -> Pair(
                    painterResource(R.drawable.baseline_favorite_24),
                    Color.Red
                )
                "comment" -> Pair(
                    painterResource(R.drawable.baseline_mode_comment),
                    Color(0xFF42A5F5) // biru muda
                )
                "commentReply" -> Pair(
                    painterResource(R.drawable.baseline_mode_comment),
                    Color(0xFF7E57C2) // ungu
                )
                "askFriendRequest" -> Pair(
                    painterResource(R.drawable.baseline_person_add_alt_1_24),
                    Color.Blue
                )
                "acceptedFriendRequest" -> Pair(
                    painterResource(R.drawable.baseline_check_24),
                    Color.Green
                )
                else -> Pair(null, Color.Transparent)
            }

            // Tampilkan icon jika tersedia
            if (iconPainter != null) {
                Icon(
                    painter = iconPainter,
                    contentDescription = "Notification Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(backgroundColor)
                        .align(Alignment.BottomEnd)
                        .padding(2.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(8.dp),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(username)
                }
                append(" $notificationTitle   ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                    append(notificationTimeStamp)
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun NotificationTilePreview() {
    NotificationTile(
        notificationTitle = "liked your post adas asda dfd fgdfg dfgdfg dfgdf dfgdf dfgdf",
        username = "Wi wok detok",
        notificationImageProfile = R.drawable.logo,
        notificationTimeStamp = "4h",
        onTileClick = {},
        notificationCategory = "comment",
    )
}