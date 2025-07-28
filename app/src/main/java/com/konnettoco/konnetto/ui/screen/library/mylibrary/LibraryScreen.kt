package com.konnettoco.konnetto.ui.screen.library.mylibrary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.theme.KonnettoTheme

@Composable
fun LibraryPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit
) {
    LibraryPageContent(
        modifier = modifier,
        onBackClick = onBackClick,
        onMoreVertClick = onMoreVertClick
    ) 
}

@Composable
fun LibraryPageContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LibraryPageTopBar(
                onBackClick = onBackClick,
                onMoreVertClick = onMoreVertClick
            ) 
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.image_mascot),
                    contentDescription = null
                )
                Text(
                    text = "Library Picks coming soon! Stay tuned for amazing contents.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryPageTopBar(
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back),
                    contentDescription = "back button",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Your Library",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(
                onClick = onMoreVertClick,
                enabled = false,
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more option",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LibraryPageScreenPreview() {
    KonnettoTheme {
        LibraryPageScreen(
            onBackClick = {},
            onMoreVertClick = {}
        )
    }
}

@Preview(showBackground = true,  uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,)
@Composable
private fun LibraryPageScreenDarkPreview() {
    KonnettoTheme {
        LibraryPageScreen(
            onBackClick = {},
            onMoreVertClick = {}
        )
    }
}