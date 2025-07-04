package com.zulfadar.konnetto.ui.friendlist.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import com.zulfadar.konnetto.R

@Composable
fun FriendTile(
    modifier: Modifier = Modifier,
    onFriendTileClick: () -> Unit,
    profilePict: Int?,
    displayname: String,
    username: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onFriendTileClick }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box {
            Image(
                painter = if (profilePict != null) painterResource(profilePict) else painterResource(R.drawable.img),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.width(180.dp),
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement =  Arrangement.Top
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = displayname,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = username,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            modifier = Modifier
                .height(40.dp)
                .width(110.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                Color.White
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            enabled = true
        ) {
            Text(
                text = "Unfriend",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendTilePrev() {
    FriendTile(
        onFriendTileClick = {},
        profilePict = null,
        displayname = "Comoli Harcourt asda asda asda asda asdasf dg",
        username = "halo",
    )
}