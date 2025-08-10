package com.konnettoco.konnetto.ui.screen.library.mylibrary

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.konnettoco.konnetto.ui.navigation.TabItem
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LibraryPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit
) {
    val tabItems = listOf(
        TabItem(title = "Watching"),
        TabItem(title = "Completed"),
        TabItem(title = "Plan to watch"),
    )
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        tabItems.size
    }
    val coroutineScope = rememberCoroutineScope()
    val selectedTabIndex by remember {
        derivedStateOf { pagerState.currentPage }
    }
//    Column(
//        modifier = modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            modifier = Modifier
//                .size(100.dp)
//                .clip(CircleShape),
//            painter = painterResource(R.drawable.image_mascot),
//            contentDescription = null
//        )
//        Text(
//            text = "Library Picks coming soon! Stay tuned for amazing contents.",
//            style = MaterialTheme.typography.bodyMedium,
//            textAlign = TextAlign.Justify,
//            modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp),
//            color = MaterialTheme.colorScheme.onSurface
//        )
//    }
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
            LibraryTabs(
                tabItems = tabItems,
                pagerState = pagerState,
                tabCoroutineScope = coroutineScope,
                selectedTabIndex = selectedTabIndex
            )
            LibraryPageContent(
                modifier = modifier,
                pagerState = pagerState
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LibraryPageContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
    ) { index ->
        when(index) {
            0 -> {
                Column(
                    modifier = modifier.fillMaxSize(),
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
                        text = "You are not watching anything yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 80.dp, vertical = 12.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            1 -> {
                Column(
                    modifier = modifier.fillMaxSize(),
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
                        text = "You are not complete anything yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 80.dp, vertical = 12.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            2 -> {
                Column(
                    modifier = modifier.fillMaxSize(),
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
                        text = "There is no plan yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 80.dp, vertical = 12.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LibraryTabs(
    modifier: Modifier = Modifier,
    tabItems: List<TabItem>,
    pagerState: PagerState,
    tabCoroutineScope: CoroutineScope,
    selectedTabIndex: Int,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 18.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified,
            indicator = {},
            divider = {}
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.Transparent),
                    selected = index == selectedTabIndex,
                    selectedContentColor = Color.Transparent,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                    onClick = {
                        // Saat klik tab, scroll ke page tanpa trigger konflik dari swipe
                        tabCoroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = item.title,
                            color = if (selectedTabIndex == index) Color.White else MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    },
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
                    text = "Your Library",
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

@Preview(showBackground = true,  uiMode = Configuration.UI_MODE_NIGHT_YES,)
@Composable
private fun LibraryPageScreenDarkPreview() {
    KonnettoTheme {
        LibraryPageScreen(
            onBackClick = {},
            onMoreVertClick = {}
        )
    }
}