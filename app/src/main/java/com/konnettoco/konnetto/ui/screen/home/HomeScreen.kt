package com.konnettoco.konnetto.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.FakeUserDataSource.currentUserDummy
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy1
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy2
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy3
import com.konnettoco.konnetto.data.model.Post
import com.konnettoco.konnetto.di.Injection
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.components.PostCardItem
import com.konnettoco.konnetto.ui.navigation.TabItem
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import com.konnettoco.konnetto.ui.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepositoy(),
        )
    ),
    navigateToComment: () -> Unit,
    navigateToLikedBy: () -> Unit,
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
                    navigateToLikedBy = navigateToLikedBy
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    postings: List<Post>,
    navigateToComments: () -> Unit,
    navigateToLikedBy: () -> Unit,
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
        modifier = modifier.fillMaxWidth()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HomeTabs(
                posts = postings,
                onCommentClick = navigateToComments,
                onLikeCountClick = navigateToLikedBy
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabs(
    posts: List<Post>,
    onCommentClick: () -> Unit,
    onLikeCountClick: () -> Unit
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
                            items(
                                items = posts,
                                key = { post -> post.id } // <- gunakan ID unik dari post
                            ) { data ->
                                PostCardItem(
                                    displayname = data.author.displayname,
                                    username = data.author.username,
                                    timestamp = data.createdAt.toString(),
                                    profilePict = data.author.photo,
                                    image = data.image,
                                    caption = data.caption,
                                    onCommentsClick = onCommentClick,
                                    totalLike = data.totalLike,
                                    totalComment = data.totalComments,
                                    totalShare = data.totalShare,
                                    isLiked = data.isLiked,
                                    onLikedCountClick = onLikeCountClick,
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
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(40.dp))
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



@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    KonnettoTheme {
        HomeContent(
            postings = listOf(
                Post(
                    id = 0,
                    author = currentUserDummy,
                    image = R.drawable.memespongebob,
                    caption = "test",
                    isLiked = true,
                    isSaved = false,
                    totalLike = 1,
                    totalComments = 2,
                    createdAt = 2,
                    updatedAt = "2025-07-05T14:30:00Z"
                ),
                Post(
                    id = 1,
                    author = otherUserDummy1,
                    image = null,
                    caption = "Bjir wkwkwkwkwkwkwk",
                    isLiked = false,
                    isSaved = false,
                    totalLike = 200,
                    totalComments = 200,
                    totalShare = 0,
                    createdAt = 2,
                    updatedAt = "2025-07-04T13:00:00Z"
                ),
                Post(
                    id = 2,
                    caption = "America ya, Halo, Halo, Halo, Halo, Halo",
                    image = null,
                    isLiked = true,
                    isSaved = false,
                    author = otherUserDummy2,
                    totalLike = 1,
                    totalComments = 0,
                    totalShare = 1,
                    createdAt = 5,
                ),
                Post(
                    id = 3,
                    caption = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at metus id eros dapibus venenatis. Duis volutpat, lacus in fermentum dapibus, mauris sapien rhoncus sapien, nec feugiat nisl risus non risus. Nulla facilisi. Cras eget felis nec odio tincidunt elementum. Curabitur sit amet leo vel nunc posuere dapibus. Aliquam erat volutpat. Suspendisse tincidunt arcu at lorem efficitur, in cursus nisl maximus. Integer tristique tincidunt massa, eu sollicitudin nisi suscipit non. Pellentesque in eros eget justo eleifend hendrerit. Aenean scelerisque, magna non convallis rhoncus, lorem elit ullamcorper augue, sit amet tincidunt turpis nisl sed nulla. Nam a leo at justo venenatis sodales. Vivamus id dui nec nulla sagittis vestibulum. Integer tempus purus sed turpis pharetra varius.",
                    image = R.drawable.memespongebob,
                    isLiked = false,
                    isSaved = false,
                    author = otherUserDummy3,
                    totalLike = 0,
                    totalComments = 0,
                    totalShare = 0,
                    createdAt = 10,
                ),
                Post(
                    id = 4,
                    caption = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at metus id eros dapibus venenatis. Duis volutpat, lacus in fermentum dapibus, mauris sapien rhoncus sapien, nec feugiat nisl risus non risus. Nulla facilisi. Cras eget felis nec odio tincidunt elementum. Curabitur sit amet leo vel nunc posuere dapibus. Aliquam erat volutpat. Suspendisse tincidunt arcu at lorem efficitur, in cursus nisl maximus. Integer tristique tincidunt massa, eu sollicitudin nisi suscipit non. Pellentesque in eros eget justo eleifend hendrerit. Aenean scelerisque, magna non convallis rhoncus, lorem elit ullamcorper augue, sit amet tincidunt turpis nisl sed nulla. Nam a leo at justo venenatis sodales. Vivamus id dui nec nulla sagittis vestibulum. Integer tempus purus sed turpis pharetra varius.",
                    image = null,
                    isLiked = true,
                    isSaved = false,
                    author = otherUserDummy3,
                    totalLike = 10,
                    totalComments = 5,
                    totalShare = 3,
                    createdAt = 11,
                ),
            ),
            navigateToComments = {},
            onMenuClick = {},
            onSearchClick = {},
            navigateToLikedBy = {},
        )
    }
}