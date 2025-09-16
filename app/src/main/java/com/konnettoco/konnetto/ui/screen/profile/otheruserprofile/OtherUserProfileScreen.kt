package com.konnettoco.konnetto.ui.screen.profile.otheruserprofile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.model.Post
import com.konnettoco.konnetto.data.model.User
import com.konnettoco.konnetto.di.Injection
import com.konnettoco.konnetto.ui.common.OverlayManager
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.components.PostCardItem
import com.konnettoco.konnetto.ui.navigation.TabItem
import com.konnettoco.konnetto.ui.navigation.WatchingTabItem
import com.konnettoco.konnetto.ui.screen.profile.userprofile.ProfileViewModel
import com.konnettoco.konnetto.ui.screen.profile.userprofile.components.WatchCardItem
import com.konnettoco.konnetto.ui.viewModelFactory.OtherUserProfileViewModelFactory
import com.konnettoco.konnetto.ui.viewModelFactory.ProfileViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OtherUserProfileScreen(
    onBackClick: () -> Unit,
    onShareBtnClick: () -> Unit,
    onAddFrndBtnClick: () -> Unit,
    onFriendCountClick: () -> Unit,
    modifier: Modifier = Modifier,
    userId: Long,
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            Injection.provideRepositoy(),
            Injection.provideLibraryRepository(),
        )
    ),
    viewModel2: OtherUserProfileViewModel = viewModel(
        factory = OtherUserProfileViewModelFactory(
            Injection.provideUserRepository(),
            Injection.provideRepositoy(),
            userId = userId,
        )
    )
) {
    val userState by viewModel2.otherUserUiState.collectAsState(initial = UiState.Loading)
    val postState by viewModel2.otherUserPosts.collectAsState(initial = UiState.Loading)
    val watchingState by viewModel.uiStateWatching.collectAsState(initial = UiState.Loading)


    val lazyListState = rememberLazyListState()
    val tabItems = listOf(
        TabItem(title = "Post"),
        TabItem(title = "Picks")
    )
    val watchTabItem = listOf(
        WatchingTabItem(
            title = "Currently\nWatching"
        ),
        WatchingTabItem(title = "Completed"),
        WatchingTabItem(title = "Plan to\nWatch")
    )
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        tabItems.size
    }
    val pagerWatchState = rememberPagerState(
        initialPage = 0
    ) {
        watchTabItem.size
    }

    // Untuk update tabIndex saat user swipe
    val selectedTabIndex by remember {
        derivedStateOf { pagerState.currentPage }
    }
    val selectWatchTabIndex by remember {
        derivedStateOf { pagerWatchState.currentPage }
    }
    //commentSection
    var showCommentSectionSheet by rememberSaveable { mutableStateOf(false) }
    //liked by section
    var showLikedBySectionSheet by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            when (userState) {
                is UiState.Success -> {
                    val user = (userState as UiState.Success<User>).data
                    OtherUserProfileTopAppBar(
                        username = user.username,
                        onBackClick = onBackClick
                    )
                }
                else -> {
                    OtherUserProfileTopAppBar(
                        username = "",
                        onBackClick = onBackClick
                    )
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            state = lazyListState,
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 150.dp,
                top = 8.dp
            ),
        ) {
            when(userState) {
                is UiState.Loading -> {
                    item {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is UiState.Success -> {
                    val user = (userState as UiState.Success<User>).data
                    item {
                        OtherUserProfileSection(
                            displayname = user.displayname,
                            username = user.username,
                            profilePict = user.photo.toString(),
                            friends = user.totalFriends,
                            totalPost = user.totalPost,
                            totalWatch = user.totalWatch,
                            follows = user.totalFollowing,
                            biography = user.bio,
                            onAddFrndBtnClick = onAddFrndBtnClick,
                            onShareBtnClick = onShareBtnClick,
                            onFriendCountClick = onFriendCountClick,
                        )
                        OtherUserWatchListTabRow(
                            modifier = modifier,
                            selectWatchTabIndex = selectWatchTabIndex,
                            watchTabItem = watchTabItem,
                            coroutineScope = coroutineScope,
                            watchPagerState = pagerWatchState
                        )
                        HorizontalPager(
                            state = pagerWatchState,
                            modifier = Modifier
                                .padding(12.dp)
                        ) { index ->
                            when (index) {
                                0 -> {
                                    Row(
                                        modifier = Modifier
                                            .horizontalScroll(rememberScrollState()),
                                        horizontalArrangement = Arrangement.Start,
                                    ) {
                                        when {
                                            watchingState is UiState.Loading -> {
                                                Box(
                                                    modifier = modifier.fillMaxSize(),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    CircularProgressIndicator()
                                                }
                                            }

                                            watchingState is UiState.Success -> {
                                                val watchingList = (watchingState as UiState.Success).data
                                                if (watchingList.isNullOrEmpty()) {
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxSize()
                                                            .padding(vertical = 61.dp),
                                                        contentAlignment = Alignment.TopCenter
                                                    ) {
                                                        Text(
                                                            fontSize = 18.sp,
                                                            color = MaterialTheme.colorScheme.onSurface,
                                                            text = "You're not completed anything yet",
                                                            modifier = Modifier.padding(16.dp)
                                                        )
                                                    }
                                                } else {
                                                    if (watchingState is UiState.Success) {
                                                        val myLibrary = (watchingState as UiState.Success).data
                                                        val watchingList = myLibrary.filter { item ->
                                                            item.currentEpisode > 0 && item.currentEpisode < item.totalEpisode
                                                        }
                                                        watchingList.forEach { data ->
                                                            WatchCardItem(
                                                                title = data.title,
                                                                posterImage = data.image,
                                                                currentEpisode = data.currentEpisode,
                                                                totalEpisode = data.totalEpisode,
                                                            )
                                                        }
                                                    }
                                                }
                                            }

                                            postState is UiState.Error || watchingState is UiState.Error -> {

                                            }
                                        }
                                    }
                                }
                                1 -> {
                                    Row(
                                        modifier = Modifier
                                            .horizontalScroll(rememberScrollState()),
                                        horizontalArrangement = Arrangement.Start,
                                    ) {
                                        when {
                                            watchingState is UiState.Loading -> {
                                                Box(
                                                    modifier = modifier.fillMaxSize(),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    CircularProgressIndicator()
                                                }
                                            }

                                            watchingState is UiState.Success -> {
                                                val watchingList = (watchingState as UiState.Success).data
                                                if (watchingList.isNullOrEmpty()) {
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxSize()
                                                            .padding(vertical = 61.dp),
                                                        contentAlignment = Alignment.TopCenter
                                                    ) {
                                                        Text(
                                                            fontSize = 18.sp,
                                                            color = MaterialTheme.colorScheme.onSurface,
                                                            text = "You're not completed anything yet",
                                                            modifier = Modifier.padding(16.dp)
                                                        )
                                                    }
                                                } else {
                                                    if (watchingState is UiState.Success) {
                                                        val myLibrary = (watchingState as UiState.Success).data
                                                        val completedList = myLibrary.filter { item ->
                                                            item.currentEpisode == item.totalEpisode && item.totalEpisode > 0
                                                        }
                                                        completedList.forEach { data ->
                                                            WatchCardItem(
                                                                title = data.title,
                                                                posterImage = data.image,
                                                                currentEpisode = data.currentEpisode,
                                                                totalEpisode = data.totalEpisode,
                                                            )
                                                        }
                                                    }
                                                }
                                            }

                                            postState is UiState.Error || watchingState is UiState.Error -> {
                                                // Tampilkan error state
                                            }
                                        }
                                    }
                                }
                                2 -> {
                                    Row(
                                        modifier = Modifier
                                            .horizontalScroll(rememberScrollState()),
                                        horizontalArrangement = Arrangement.Start,
                                    ) {
                                        when {
                                            watchingState is UiState.Loading -> {
                                                Box(
                                                    modifier = modifier.fillMaxSize(),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    CircularProgressIndicator()
                                                }
                                            }

                                            watchingState is UiState.Success -> {
                                                val watchingList = (watchingState as UiState.Success).data
                                                if (watchingList.isNullOrEmpty()) {
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxSize(),
                                                        verticalArrangement = Arrangement.Top,
                                                    ) {
                                                        Box(
                                                            modifier = Modifier
                                                                .fillMaxSize()
                                                                .padding(vertical = 61.dp),
                                                            contentAlignment = Alignment.TopCenter
                                                        ) {
                                                            Text(
                                                                fontSize = 18.sp,
                                                                color = MaterialTheme.colorScheme.onSurface,
                                                                text = "There is no plan yet",
                                                                modifier = Modifier.padding(16.dp)
                                                            )
                                                        }
                                                    }
                                                } else {
                                                    if (watchingState is UiState.Success) {
                                                        val myLibrary = (watchingState as UiState.Success).data
                                                        val PlanToWatchList = myLibrary.filter { item ->
                                                            item.currentEpisode == 0
                                                        }
                                                        PlanToWatchList.forEach { data ->
                                                            WatchCardItem(
                                                                title = data.title,
                                                                posterImage = data.image,
                                                                currentEpisode = data.currentEpisode,
                                                                totalEpisode = data.totalEpisode,
                                                            )
                                                        }
                                                    }
                                                }
                                            }

                                            postState is UiState.Error || watchingState is UiState.Error -> {
                                                // Tampilkan error state
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                    stickyHeader {
                        OtherUserContentTabRow(
                            selectedTabIndex = selectedTabIndex,
                            tabItems = tabItems,
                            coroutineScope = coroutineScope,
                            pagerState = pagerState
                        )
                    }
                    item {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) { index ->
                            when (index) {
                                0 -> {
                                    when {
                                        postState is UiState.Loading -> {
                                            Box(
                                                modifier = modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator()
                                            }
                                        }

                                        postState is UiState.Success -> {
                                            val posts = (postState as UiState.Success).data
                                            OtherUserPostList(
                                                posts = posts,
                                                onCommentClick = { showCommentSectionSheet = true },
                                                onLikeCountClick = { showLikedBySectionSheet = true }
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

                                        postState is UiState.Error || watchingState is UiState.Error -> {
                                            // Tampilkan error state
                                        }
                                    }
                                }
                                1 -> {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally
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
                                            modifier = Modifier.padding(
                                                horizontal = 100.dp,
                                                vertical = 12.dp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                is UiState.Error -> {

                }
            }
        }
    }
}

@Composable
fun OtherUserPostList(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    onCommentClick: () -> Unit,
    onLikeCountClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
    ) {
        if (posts.isNullOrEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary,
                    text = "No Post Yet",
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            posts.forEach { data ->
                PostCardItem(
                    modifier = modifier.fillMaxWidth(),
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
                    isSaved = data.isSaved,
                    onLikedCountClick = onLikeCountClick,
                    onDisplaynameClick = {},
                    onPostClick = {},
                )
            }
        }
    }
}

@Composable
fun OtherUserProfileSection(
    displayname: String,
    username: String,
    profilePict: String? = null,
    friends: Int?,
    follows: Int?,
    totalPost: Int?,
    totalWatch: Int?,
    biography: String?,
    onAddFrndBtnClick: () -> Unit,
    onShareBtnClick: () -> Unit,
    onFriendCountClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val avatarPainter = rememberAsyncImagePainter(model = profilePict)

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = avatarPainter,
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = displayname,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "@"+username,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.clickable {
                        onFriendCountClick()
                    },
                    text = friends.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Friends",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
//            Column(
//                modifier = Modifier
//                    .padding(start = 12.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = follows.toString(),
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//                Text(
//                    text = "Following",
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Normal,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//
//            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = totalPost.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Posts",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = totalWatch.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Watched",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .width(180.dp)
                    .height(40.dp),
                onClick = onAddFrndBtnClick,
                enabled = true,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = "Add Friend",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .width(180.dp)
                    .height(40.dp),
                onClick = onShareBtnClick,
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.surfaceContainerLowest
                ),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = "Share Profile",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column {
            val displayedText = if (isExpanded || (biography?.length ?: 0) <= 50) biography else "${biography?.take(100) ?: ""}..."

            if (displayedText != null) {
                Text(
                    text = displayedText,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                    lineHeight = 14.sp,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                )
            }
            if (biography?.length!! > 100) {
                Text(
                    text = if (isExpanded) "See Less" else "See More",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(start = 12.dp)
                        .align(Alignment.Start)
                )
            }
        }
        Spacer(Modifier.heightIn(8.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OtherUserWatchListTabRow(
    modifier: Modifier = Modifier,
    selectWatchTabIndex: Int,
    watchTabItem: List<WatchingTabItem>,
    coroutineScope: CoroutineScope,
    watchPagerState: PagerState,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectWatchTabIndex,
//                    edgePadding = 0.dp
    ) {
        watchTabItem.forEachIndexed { index, item ->
            Tab(
                selected = index == selectWatchTabIndex,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                onClick = {
                    // Saat klik tab, scroll ke page tanpa trigger konflik dari swipe
                    coroutineScope.launch {
                        watchPagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectWatchTabIndex == index) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            Color.LightGray
                        }
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OtherUserContentTabRow(
    selectedTabIndex: Int,
    tabItems: List<TabItem>,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
) {
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
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OtherUserProfileTopAppBar(
    username: String,
    onBackClick: () -> Unit,
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
                    modifier = Modifier.aspectRatio(0.8f),
                    contentDescription = "back button"
                )
            }
        },
        title = {
            Text(
                text = username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        },
    )
}