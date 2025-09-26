package com.konnettoco.konnetto.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.remote.response.DataItem
import com.konnettoco.konnetto.data.remote.response.SugoiPicksDataItem
import com.konnettoco.konnetto.ui.common.OverlayManager
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.components.PostCardItem
import com.konnettoco.konnetto.ui.components.PostShimmerLoading
import com.konnettoco.konnetto.ui.components.SugoiPicksCardItem
import com.konnettoco.konnetto.ui.navigation.TabItem
import com.konnettoco.konnetto.ui.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
//            Injection.provideRepositoy(),
//            Injection.provideSugoiPicksRepository()
        )
    ),
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onDisplaynameClick: (Long) -> Unit,
) {
//    val postState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    val postState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    val sugoiPicksState by viewModel.sugoiPicksState.collectAsState(initial = UiState.Loading)

    //commentSection
    var showCommentSectionSheet by rememberSaveable { mutableStateOf(false) }
    //liked by section
    var showLikedBySectionSheet by rememberSaveable { mutableStateOf(false) }

    val tabItems = listOf(
        TabItem(title = "For You"),
        TabItem(title = "Friends"),
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

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onMenuClick = onMenuClick,
                onSearchClick = onSearchClick
            )
        },
        modifier = modifier.fillMaxWidth()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HomeTabs(
                tabItems = tabItems,
                pagerState = pagerState,
                tabCoroutineScope = coroutineScope,
                selectedTabIndex = selectedTabIndex,
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                when (index) {
                    0 -> {
                        when {
                            postState is UiState.Loading -> {
                                val count = 5
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    LazyColumn(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        items(count) {
                                            PostShimmerLoading()
                                        }
                                    }
                                }
                            }

                            postState is UiState.Success -> {
                                val posts = (postState as UiState.Success).data

                                ForYouContent(
                                    posts = posts,
                                    navigateToComments = { showCommentSectionSheet = true },
                                    navigateToLikedBy = { showLikedBySectionSheet = true },
                                    onDisplaynameClick = onDisplaynameClick
                                )
                                OverlayManager(
                                    showCommentSectionSheet = showCommentSectionSheet,
                                    onDismissCommentSheet = {
                                        showCommentSectionSheet = false
                                    },
                                    showLikedBySectionSHeet = showLikedBySectionSheet,
                                    onDismissLikedBySheet = {
                                        showLikedBySectionSheet = false
                                    }
                                )
                            }
                            postState is UiState.Error -> {
                //                    val errorMsg = (uiState as UiState.Error).errorMessage
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(12.dp),
                                            text = "No Internet, check your internet connection and then try again..",
                                            color = MaterialTheme.colorScheme.onSurface,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Button(
                                            onClick = {  },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.primary
                                            ),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Text(
                                                text = "Try again",
                                                color = Color.White,
                                                style = MaterialTheme.typography.labelLarge
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        FriendPostsContent()
                    }
                    2 -> {
                        when {
                            sugoiPicksState is UiState.Loading -> {
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            sugoiPicksState is UiState.Success -> {
                                val sugoiPicks = (sugoiPicksState as UiState.Success).data

                                SugoiPicksContent(
                                    sugoiPicks = sugoiPicks,
                                    navigateToComments = { showCommentSectionSheet = true },
                                    navigateToLikedBy = { showLikedBySectionSheet = true },
                                    onDisplaynameClick = onDisplaynameClick
                                )
                                OverlayManager(
                                    showCommentSectionSheet = showCommentSectionSheet,
                                    onDismissCommentSheet = {
                                        showCommentSectionSheet = false
                                    },
                                    showLikedBySectionSHeet = showLikedBySectionSheet,
                                    onDismissLikedBySheet = {
                                        showLikedBySectionSheet = false
                                    }
                                )
                            }
                            sugoiPicksState is UiState.Error -> {
                                //                    val errorMsg = (uiState as UiState.Error).errorMessage
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(12.dp),
                                            text = "No Internet, check your internet connection and then try again..",
                                            color = MaterialTheme.colorScheme.onSurface,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Button(
                                            onClick = {  },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.primary
                                            ),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Text(
                                                text = "Try again",
                                                color = Color.White,
                                                style = MaterialTheme.typography.labelLarge
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ForYouContent(
    posts: List<DataItem>,
    navigateToComments: () -> Unit,
    navigateToLikedBy: () -> Unit,
    onDisplaynameClick: (Long) -> Unit,
) {
    if (posts.isNullOrEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.image_mascot),
                contentDescription = null
            )
            Text(
                text = "No Post Yet.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 150.dp,
                top = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = posts,
                key = { post -> post.id ?: ""}
            ) { data ->
                PostCardItem(
                    displayname = data.displayname ?: "",
                    username = data.username ?: "",
                    createdAt = data.createdAt ?: "",
                    profilePict = data.avatarUrl ?: "",
                    image = data.media?.filterNotNull()?.mapNotNull { it.url } ?: emptyList(),  // ambil hanya url yang non-null
                    caption = data.caption ?: "",
                    onCommentsClick = navigateToComments,
                    totalLike = data.likeCount ?: 0,
                    totalComment = data.commentCount ?: 0,
                    totalShare = data.shareCount ?: 0,
                    isLiked = false,
                    isSaved = false,
                    isFriend = false,
                    onLikedCountClick = navigateToLikedBy,
                    onDisplaynameClick = {
//                        data.author.id.let { onDisplaynameClick(it.toLong()) }
                    },
                    onPostClick = {},
                    onAddFriendClick = {},
                    showAddFriendButton = true
                )
            }
        }
    }
}

@Composable
fun FriendPostsContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.image_mascot),
            contentDescription = null
        )
        Text(
            text = "No post from friends yet. Make friend with some poeple now!",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp)
        )
    }
}

@Composable
fun SugoiPicksContent(
    sugoiPicks: List<SugoiPicksDataItem>,
    navigateToComments: () -> Unit,
    navigateToLikedBy: () -> Unit,
    onDisplaynameClick: (Long) -> Unit
) {
    if (sugoiPicks.isNullOrEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.image_mascot),
                contentDescription = null
            )
            Text(
                text = "Sugoi Picks coming soon! Stay tuned for amazing contents.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 150.dp,
                top = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = sugoiPicks,
                key = { sugoiPicks -> sugoiPicks.id ?: "" }
            ) { data ->
                SugoiPicksCardItem(
                    displayname = data.displayname ?: "",
                    username = data.username ?: "",
                    createdAt = data.createdAt ?: "",
                    profilePict = data.avatarUrl ?: "",
                    image = data.media?.filterNotNull()?.mapNotNull { it.url } ?: emptyList(),  // ambil hanya url yang non-null,
                    caption = data.caption ?: "",
                    totalLike = data.likeCount ?: 0,
                    totalComment = data.commentCount ?: 0,
                    totalShare = data.shareCount ?: 0,
                    isLiked = false,
                    onLikedCountClick = navigateToLikedBy,
                    onCommentsClick = navigateToComments,
                    posterImage = data.review?.posterUrl ?: "",
                    title = data.review?.animeName ?: "",
                    rating = data.review?.rating ?: 0.0,
                    releaseDate = data.createdAt.toString(),
                    genres = data.review?.genres ?: emptyList(),
                    onDisplaynameClick = {
//                        data.author.id.let { onDisplaynameClick(it.toLong()) }
                    },
                    onSugoiPicksClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabs(
    tabItems: List<TabItem>,
    pagerState: PagerState,
    tabCoroutineScope: CoroutineScope,
    selectedTabIndex: Int,
) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier.height(40.dp),
                selected = index == selectedTabIndex,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.LightGray,
                onClick = {
                    tabCoroutineScope.launch {
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
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onMenuClick()
            }) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(40.dp))
                )
            }
        },
        title = {
            Text(
                "Konnetto",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            IconButton(onClick = onSearchClick ) {
                Icon(
                    painter = painterResource(R.drawable.icons8_search),
                    contentDescription = "Search",
                    modifier = Modifier.aspectRatio(0.8f),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
//            IconButton(onClick = {} ) {
//                Icon(
//                    painter = painterResource(R.drawable.icons8_chat),
//                    contentDescription = "Search",
//                    modifier = Modifier.aspectRatio(0.8f),
//                    tint = MaterialTheme.colorScheme.onBackground
//                )
//            }
        }
    )
}