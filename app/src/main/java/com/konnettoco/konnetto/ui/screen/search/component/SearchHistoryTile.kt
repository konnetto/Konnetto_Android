package com.konnettoco.konnetto.ui.screen.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R

@Composable
fun HistorySearchTile(
    modifier: Modifier = Modifier,
    onHistorySearchClick: () -> Unit,
    historySearch: String,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().clickable { onHistorySearchClick },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.padding(start = 12.dp),
            painter = painterResource(R.drawable.access_time_filled_24),
            contentDescription = null,
            tint = Color.LightGray
        )
        Text(
            modifier = Modifier.weight(1f).padding(start = 8.dp, end = 8.dp),
            text = historySearch,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
        IconButton(onClick = onDeleteClick ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Search",
                modifier = Modifier.aspectRatio(0.6f),
                tint = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistorySearchTilePrev() {
    HistorySearchTile(
        historySearch = "Kucing lucu",
        onDeleteClick = {},
        onHistorySearchClick = {}
    )
}