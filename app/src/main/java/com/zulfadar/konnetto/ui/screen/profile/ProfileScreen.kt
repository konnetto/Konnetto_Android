package com.zulfadar.konnetto.ui.screen.profile

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SheetState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.CurrentlyWatching
import com.zulfadar.konnetto.data.model.Post
import com.zulfadar.konnetto.di.Injection
import com.zulfadar.konnetto.ui.common.UiState
import com.zulfadar.konnetto.ui.components.PostCardItem
import com.zulfadar.konnetto.ui.navigation.TabItem
import com.zulfadar.konnetto.ui.navigation.WatchingTabItem
import com.zulfadar.konnetto.ui.screen.commentSection.CommentSection
import com.zulfadar.konnetto.ui.screen.profile.components.WatchCardItem
import com.zulfadar.konnetto.ui.theme.KonnettoTheme
import com.zulfadar.konnetto.ui.viewModelFactory.ProfileViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onShareBtnClick: () -> Unit,
    onEdtBtnClick: () -> Unit,
    showCommentSectionSheet: Boolean,
    commentSectionSheetState: SheetState,
    onDismissCommentSheet: () -> Unit,
    onCommentClick: () -> Unit,
    onFriendCountClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            Injection.provideRepositoy(),
            Injection.provideCurretnlyWatchingRepository(),
        )
    )
) {
    val postState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    val currentlyWatchingState by viewModel.uiStateCurrentlyWatching.collectAsState(initial = UiState.Loading)

    when {
        postState is UiState.Loading || currentlyWatchingState is UiState.Loading -> {
            viewModel.getAllPostings()
            viewModel.getAllCurrentlyWatching()
            // Optional: tampilkan loading indicator
        }

        postState is UiState.Success && currentlyWatchingState is UiState.Success -> {
            val posts = (postState as UiState.Success).data
            val currentlyWatchingList = (currentlyWatchingState as UiState.Success).data

            ProfileContent(
                modifier = modifier,
                displayname = "Uzumai Uchiha bambank",
                username = "BamBank",
                profilePict = R.drawable.logo.toString(),
                friends = 500,
                follows = 12,
                biography = "\uD83D\uDC68\u200D\uD83D\uDCBB Mobile & Web Developer | Kotlin • Flutter • Java Spring\n" +
                        "\uD83C\uDF93 S1 Teknologi Informasi | Bangkit 2023 Graduate\n" +
                        "\uD83D\uDE80 Passionate about building useful & meaningful apps",
                posts = posts,
                currentlyWatch = currentlyWatchingList,
                onBackClick = onBackClick,
                onCommentClick = onCommentClick,
                showCommentSectionSheet = showCommentSectionSheet,
                commentSectionState = commentSectionSheetState,
                onDismissCommentSheet = onDismissCommentSheet,
                onEdtBtnClick = onEdtBtnClick,
                onShareBtnClick = onShareBtnClick,
                onFriendCountClick = onFriendCountClick,
            )
        }

        postState is UiState.Error || currentlyWatchingState is UiState.Error -> {
            // Tampilkan error state
        }
    }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    displayname: String,
    username: String,
    profilePict: String?,
    friends: Int?,
    follows: Int?,
    biography: String?,
    posts: List<Post>,
    currentlyWatch: List<CurrentlyWatching>,
    showCommentSectionSheet: Boolean,
    commentSectionState: SheetState,
    onDismissCommentSheet: () -> Unit,
    onCommentClick: () -> Unit,
    onEdtBtnClick: () -> Unit,
    onShareBtnClick: () -> Unit,
    onFriendCountClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val tabItems = listOf(
        TabItem(title = "Post"),
        TabItem(title = "My Picks")
    )
    val watchTabItem = listOf(
        WatchingTabItem(title = "Currently Watching"),
        WatchingTabItem(title = "Completed"),
        WatchingTabItem(title = "Plan to Watch")
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
    Scaffold(
        topBar = {
            ProfileTopAppBar(
                username = username,
                onBackClick = onBackClick
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
           state = lazyListState,
           modifier = modifier
               .padding(paddingValues)
               .fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 150.dp, // atau kira-kira setinggi BottomBar
                top = 8.dp
            ),
        ) {
            item {
                ProfileSection(
                    displayname = displayname,
                    username = username,
                    profilePict = profilePict,
                    friends = friends,
                    follows = follows,
                    biography = biography,
                    onEdtBtnClick = onEdtBtnClick,
                    onShareBtnClick = onShareBtnClick,
                    onFriendCountClick = onFriendCountClick,
                )
            }
            item {
                ScrollableTabRow(
                    selectedTabIndex = selectWatchTabIndex,
                    edgePadding = 0.dp
                ) {
                    watchTabItem.forEachIndexed { index, item ->
                        Tab(
                            selected = index == selectWatchTabIndex,
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                            onClick = {
                                // Saat klik tab, scroll ke page tanpa trigger konflik dari swipe
                                coroutineScope.launch {
                                    pagerWatchState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Text(
                                    text = item.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            },
                        )
                    }
                }
            }
            item {
                HorizontalPager(
                    state = pagerWatchState,
                    modifier = Modifier
                        .padding(12.dp)
                ) { index ->
                    when (index) {
                        0 -> {
                            Row(
                                modifier = Modifier
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                if (currentlyWatch.isNullOrEmpty()) {
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
                                    currentlyWatch.forEach { data ->
                                        WatchCardItem(
                                            title = data.title,
                                            posterImage = data.poster,
                                        )
                                    }
                                }
                            }
                        }
                        1 -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalArrangement = Arrangement.Center
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
                                        text = "You're not completed anything yet",
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                        2 -> {
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
                        }
                    }
                }
            }

            stickyHeader {
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

            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 600.dp)
                ) { index ->
                    when (index) {
                        0 -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Top
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
        if (showCommentSectionSheet) {
            CommentSection(
                commentSheetState = commentSectionState,
                onDismissCommentSheet = onDismissCommentSheet
            )
        }
    }
}

@Composable
fun ProfileSection(
    displayname: String,
    username: String,
    profilePict: String?,
    friends: Int?,
    follows: Int?,
    biography: String?,
    onEdtBtnClick: () -> Unit,
    onShareBtnClick: () -> Unit,
    onFriendCountClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(profilePict?.toInt() ?: R.drawable.img),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(130.dp)
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
                    .padding(start = 12.dp),
            ) {
                Text(
                    text = displayname,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = username,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )

            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 50.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = onEdtBtnClick,
                enabled = true,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            Button(
                modifier = Modifier.padding(start = 8.dp),
                onClick = onShareBtnClick,
                enabled = true,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = "Share Profile",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
        Spacer(Modifier.heightIn(8.dp))
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
                    text = follows.toString(),
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
                    text = follows.toString(),
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

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileTopAppBar(
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
            Text("Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    KonnettoTheme {
        val dummySheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ProfileContent(
            displayname = "Uzumaki Uchiha bambank",
            username = "Bambank",
            profilePict = R.drawable.logo.toString(),
            friends = 500,
            follows = 50,
            biography = "AwikWok awok awok asaok asdao asdas asdas sdaddf fgrg rthr rthr wew erge rrth rthrth rthr rthrthr yj6uj6 6yj6yj6 6yj6yj 6yj6 lr ehr yjtyj  ege",
            posts = listOf(
                Post(
                    id = 0,
                    displayname = "bambank",
                    username = "Bambank",
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
                    displayname = "bambank",
                    username = "Bambank",
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
                    displayname = "bambank",
                    username = "Bambank",
                    profilePict = R.drawable.logo,
                    caption = "Awikwok Test",
                    image = R.drawable.memespongebob,
                    timestamp = "16 h",
                    comments = null,
                    isLiked = false,
                    isSaved = false,
                )
            ),
            onBackClick = {},
            onCommentClick = {},
            currentlyWatch = listOf(
                CurrentlyWatching(
                    id = 0,
                    title = "Spongebob",
                    poster = R.drawable.memespongebob
                ),
                CurrentlyWatching(
                    id = 1,
                    title = "Spongebob",
                    poster = R.drawable.memespongebob
                ),
                CurrentlyWatching(
                    id = 2,
                    title = "Spongebob",
                    poster = R.drawable.memespongebob
                ),
                CurrentlyWatching(
                    id = 3,
                    title = "Spongebob",
                    poster = R.drawable.memespongebob
                ),
                CurrentlyWatching(
                    id = 4,
                    title = "Spongebob",
                    poster = R.drawable.memespongebob
                ),
            ),
            showCommentSectionSheet = false,
            commentSectionState = dummySheetState,
            onDismissCommentSheet = {},
            onEdtBtnClick = {},
            onShareBtnClick = {},
            onFriendCountClick = {},
        )
    }
}

