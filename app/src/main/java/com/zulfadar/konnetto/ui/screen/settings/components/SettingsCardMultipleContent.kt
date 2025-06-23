package com.zulfadar.konnetto.ui.screen.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R

@Composable
fun SettingsCardMultipleContent(
    modifier: Modifier = Modifier,
    title: String,
    title2: String,
    title3: String,
    desrciption: String,
    desrciption2: String,
    desrciption3: String,
    icon: Int,
    icon2: Int,
    icon3: Int,
    onItem1Click: () -> Unit,
    onItem2Click: () -> Unit,
    onItem3Click: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(1.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItem1Click }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.padding(
                        end = 90.dp
                    )
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = desrciption,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = modifier.width(60.dp))
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItem1Click }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.padding(
                        end = 90.dp
                    )
                ) {
                    Text(
                        text = title2,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = desrciption2,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = modifier.width(50.dp))
                Icon(
                    painter = painterResource(icon2),
                    contentDescription = null
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItem1Click }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.padding(
                        end = 90.dp
                    )
                ) {
                    Text(
                        text = title3,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = desrciption3,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = modifier.width(50.dp))
                Icon(
                    painter = painterResource(icon3),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsCardMultipleContentPreview() {
    SettingsCardMultipleContent(
        title = "Notifications",
        title2 = "Language",
        title3 = "Dark Mode",
        desrciption2 = "Manage your notification references",
        desrciption3 = "Manage your notification references",
        desrciption = "Manage your notification references",
        icon = R.drawable.baseline_keyboard_arrow_right,
        onItem1Click = {},
        onItem2Click = {},
        onItem3Click = {},
        icon2 = R.drawable.baseline_keyboard_arrow_right,
        icon3 = R.drawable.baseline_keyboard_arrow_right,
    )
}