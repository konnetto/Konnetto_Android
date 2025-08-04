package com.konnettoco.konnetto.ui.screen.saved

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import kotlinx.coroutines.launch

@Composable
fun SavedPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit
) {
    SavedPageContent(
        modifier = modifier,
        onBackClick = onBackClick,
        onMoreVertClick = onMoreVertClick
    )
}

@Composable
fun SavedPageContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SavedPageTopBar(
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
            HomeTabs(
                onCommentClick = {},
                onLikeCountClick = {}
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabs(
//    posts: List<Post>,
//    sugoiPicks: List<SugoiPicks>,
    onCommentClick: () -> Unit,
    onLikeCountClick: () -> Unit
) {
    val tabItems = listOf(
        TabItem(title = "Post"),
        TabItem(title = "Sugoi Picks")
    )
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        tabItems.size
    }

    // Untuk update tabIndex saat user swipe
    val selectedTabIndex by remember {
        derivedStateOf { pagerState.currentPage }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier.height(40.dp),
                    selected = index == selectedTabIndex,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = item.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            when (index) {
                0 -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No Saved Post Yet.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
//                    if (posts.isNullOrEmpty()) {
//                        Column(
//                            modifier = Modifier.fillMaxSize(),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//                            Text(
//                                text = "No Saved Post Yet.",
//                                style = MaterialTheme.typography.bodyMedium,
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.SemiBold,
//                                modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp),
//                                color = MaterialTheme.colorScheme.onSurface
//                            )
//                        }
//                    } else {
//                        LazyColumn(
//                            modifier = Modifier.fillMaxSize(),
//                            contentPadding = PaddingValues(
//                                bottom = 150.dp, // atau kira-kira setinggi BottomBar
//                                top = 8.dp
//                            ),
//                            verticalArrangement = Arrangement.spacedBy(8.dp),
//                        ) {
//                            items(
//                                items = posts,
//                                key = { post -> post.id } // <- gunakan ID unik dari post
//                            ) { data ->
//                                PostCardItem(
//                                    displayname = data.author.displayname,
//                                    username = data.author.username,
//                                    timestamp = data.createdAt.toString(),
//                                    profilePict = data.author.photo,
//                                    image = data.image,
//                                    caption = data.caption,
//                                    onCommentsClick = onCommentClick,
//                                    totalLike = data.totalLike,
//                                    totalComment = data.totalComments,
//                                    totalShare = data.totalShare,
//                                    isLiked = data.isLiked,
//                                    onLikedCountClick = onLikeCountClick,
//                                )
//                            }
//                        }
//                    }
                }
                1 -> {
                    Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "No Saved Sugoi Picks Yet.",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
//                    if (sugoiPicks.isNullOrEmpty()) {
//                        Column(
//                            modifier = Modifier.fillMaxSize(),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//                            Text(
//                                text = "No Saved Sugoi Picks Yet.",
//                                style = MaterialTheme.typography.bodyMedium,
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.SemiBold,
//                                modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp),
//                                color = MaterialTheme.colorScheme.onSurface
//                            )
//                        }
//                    } else {
//                        LazyColumn(
//                            modifier = Modifier.fillMaxSize(),
//                            contentPadding = PaddingValues(
//                                bottom = 150.dp, // atau kira-kira setinggi BottomBar
//                                top = 8.dp
//                            ),
//                            verticalArrangement = Arrangement.spacedBy(8.dp),
//                        ) {
//                            items(
//                                items = sugoiPicks,
//                                key = { sugoiPicks -> sugoiPicks.id } // <- gunakan ID unik dari post
//                            ) { data ->
//                                SugoiPicksCardItem(
//                                    displayname = data.author.displayname,
//                                    username = data.author.username,
//                                    timestamp = data.createdAt.toString(),
//                                    profilePict = data.author.photo,
//                                    image = data.image,
//                                    caption = data.caption,
//                                    totalLike = data.totalLike,
//                                    totalComment = data.totalComments,
//                                    totalShare = data.totalShare,
//                                    isLiked = data.isLiked,
//                                    onLikedCountClick = onLikeCountClick,
//                                    onCommentsClick = onCommentClick,
//                                    posterImage = data.posterImage,
//                                    title = data.title,
//                                    rating = data.rating,
//                                    releaseDate = data.createdAt.toString(),
//                                    Genres = data.genres
//                                )
//                            }
//                        }
//                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedPageTopBar(
    modifier: Modifier = Modifier,
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
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Saved",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        actions = {
            IconButton(
                onClick = onMoreVertClick,
                enabled = false,
            ) {
                androidx.compose.material.Icon(
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
private fun SavedPageScreenPreview() {
    KonnettoTheme {
        SavedPageScreen(
            onBackClick = {},
            onMoreVertClick = {}
        )
    }
}