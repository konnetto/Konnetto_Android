package com.zulfadar.konnetto.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.Post
import com.zulfadar.konnetto.di.Injection
import com.zulfadar.konnetto.ui.ViewModelFactory
import com.zulfadar.konnetto.ui.common.UiState
import com.zulfadar.konnetto.ui.components.PostCardItem
import com.zulfadar.konnetto.ui.navigation.TabItem
import com.zulfadar.konnetto.ui.theme.KonnettoTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepositoy(),
            Injection.provideCurretnlyWatchingRepository(),
            Injection.provideNotificationsRepository(),
            Injection.provideFriendRequestsRepository()
        )
    ),
    navigateToComment: () -> Unit,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is  UiState.Loading -> {
                viewModel.getAllPostings()
            }
            is UiState.Success -> {
                HomeContent(
                    postings = uiState.data,
                    modifier = modifier,
                    navigateToComments = navigateToComment,
                    onMenuClick = onMenuClick,
                    onSearchClick = onSearchClick,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    postings: List<Post>,
    navigateToComments: () -> Unit,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                onMenuClick = onMenuClick,
                onSearchClick = onSearchClick
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .height(1500.dp)
                .padding(paddingValues)
        ) {
            HomeTabs(
                posts = postings,
                onCommentClick = navigateToComments
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabs(
    posts: List<Post>,
    onCommentClick: () -> Unit
) {
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

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                    onClick = {
                        // Saat klik tab, scroll ke page tanpa trigger konflik dari swipe
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = item.title,
                            fontSize = 16.sp,
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
                                bottom = 150.dp, // atau kira-kira setinggi BottomBar
                                top = 8.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(posts) { data ->
                                PostCardItem(
                                    displayname = data.displayname,
                                    username = data.username,
                                    timestamp = data.timestamp,
                                    profilePict = data.profilePict,
                                    image = data.image,
                                    caption = data.caption,
                                    onCommentsClick = onCommentClick,
                                )
                            }
                        }
                    }
                }
                1 -> {
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
                            text = "No post from friends yet. Make friend with some poeple now!",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp)
                        )
                    }
                }
                2 -> {
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
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar(
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
                        .size(30.dp)
                        .clip(CircleShape)
                )
            }
        },
        title = {
            Text("Konnetto",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            IconButton(onClick = {} ) {
                Icon(
                    painter = painterResource(R.drawable.icons8_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(onClick = onSearchClick ) {
                Icon(
                    painter = painterResource(R.drawable.icons8_chat),
                    contentDescription = "Search",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    KonnettoTheme {
        val dummySheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        HomeContent(
            postings = listOf(
                Post(
                    id = 0,
                    displayname = "Char Aznable",
                    username = "charaznable",
                    profilePict = R.drawable.logo,
                    caption = "Awikwok Test",
                    image = R.drawable.memespongebob,
                    timestamp = "16 h",
                    comments = null,
                    isLiked = false,
                    isSaved = false,
                ),
                Post(
                    id = 1,
                    displayname = "Char Aznable",
                    username = "charaznable",
                    profilePict = R.drawable.logo,
                    caption = "Awikwok Test",
                    image = null,
                    timestamp = "16 h",
                    comments = null,
                    isLiked = false,
                    isSaved = false,
                ),
                Post(
                    id = 2,
                    displayname = "Char Aznable",
                    username = "charaznable",
                    profilePict = R.drawable.logo,
                    caption = "Awikwok Test",
                    image = R.drawable.memespongebob,
                    timestamp = "16 h",
                    comments = null,
                    isLiked = false,
                    isSaved = false,
                ),
            ),
            navigateToComments = {},
            onMenuClick = {},
            onSearchClick = {},
        )
    }
}