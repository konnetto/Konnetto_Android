package com.zulfadar.konnetto.ui.screen.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.NotificationsTile
import com.zulfadar.konnetto.di.Injection
import com.zulfadar.konnetto.ui.common.UiState
import com.zulfadar.konnetto.ui.navigation.TabItem
import com.zulfadar.konnetto.ui.screen.notification.component.NotificationTile
import com.zulfadar.konnetto.ui.theme.KonnettoTheme
import com.zulfadar.konnetto.ui.viewModelFactory.NotificationViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onMoreVertClick: () -> Unit,
    onNotificationTileClick: () -> Unit,
    onSlideToDelete: () -> Unit,
    viewModel: NotificationViewModel = viewModel(
        factory = NotificationViewModelFactory(
            Injection.provideNotificationsRepository()
        )
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is  UiState.Loading -> {
                viewModel.getAllNotifications()
            }
            is UiState.Success -> {
                NotificationPageContent(
                    modifier = modifier,
                    notifications = uiState.data,
                    onNotificationTileClick = onNotificationTileClick,
                    onSlideToDelete = onSlideToDelete,
                    onMoreVertClick = onMoreVertClick
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationPageContent(
    modifier: Modifier = Modifier,
    notifications: List<NotificationsTile>,
    onNotificationTileClick: () -> Unit,
    onMoreVertClick: () -> Unit,
    onSlideToDelete: () -> Unit,
) {
    val tabItems = listOf(
        TabItem(title = "All"),
        TabItem(title = "Likes"),
        TabItem(title = "Comment"),
        TabItem(title = "Requests")
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

    Scaffold(
        topBar = {
            NotificationTopAppBar(
                onMoreVertClick = onMoreVertClick
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
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
                                .background(if (selectedTabIndex == index) MaterialTheme.colorScheme.surfaceContainerLowest else Color.Transparent),
                            selected = index == selectedTabIndex,
                            selectedContentColor = Color.Transparent,
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
                                    color = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                        )
                    }
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { index ->
                // Filter notifikasi berdasarkan tab
                val filteredNotifications = when (index) {
                    1 -> notifications.filter {
                        it.notificationCategory in listOf("like", "likedComment")
                    }
                    2 -> notifications.filter {
                        it.notificationCategory in listOf("comment", "commentReply")
                    }
                    3 -> notifications.filter {
                        it.notificationCategory in listOf("askFriendRequest", "acceptedFriendRequest")
                    }
                    else -> notifications // Tab "All"
                }

                // Tampilkan pesan kosong jika tidak ada notifikasi
                if (filteredNotifications.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No notification yet",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    // Tampilkan daftar notifikasi
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 12.dp)
                    ) {
                        items(filteredNotifications) { data ->
                            NotificationTile(
                                username = data.notificationUsername,
                                notificationTitle = data.notificationTitle,
                                notificationImageProfile = data.notificationProfile,
                                notificationTimeStamp = data.notificationTimeStamp,
                                onTileClick = onNotificationTileClick,
                                notificationCategory = data.notificationCategory
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NotificationTopAppBar(
    onMoreVertClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        title = {
            Text(
                text = "Notification",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(
                onClick = onMoreVertClick,
                enabled = false,
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more option",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun NotificationScreenPreview() {
    KonnettoTheme {
        NotificationPageContent(
            notifications = listOf(
                NotificationsTile(
                    notificationId = 0,
                    notificationTitle = "liked your post",
                    notificationUsername = "Wi wok de tok",
                    notificationTimeStamp = "4h",
                    notificationProfile = R.drawable.logo,
                    notificationCategory = "like"
                ),
                NotificationsTile(
                    notificationId = 1,
                    notificationTitle = "liked your comment",
                    notificationUsername = "Fufufafa",
                    notificationTimeStamp = "5h",
                    notificationProfile = R.drawable.logo,
                    notificationCategory = "comment"
                ),
                NotificationsTile(
                    notificationId = 2,
                    notificationTitle = "send you friend request",
                    notificationUsername = "Wok Wok",
                    notificationTimeStamp = "6h",
                    notificationProfile = R.drawable.logo,
                    notificationCategory = "askFriendRequest"
                ),
                NotificationsTile(
                    notificationId = 3,
                    notificationTitle = "accepted your friend request",
                    notificationUsername = "Uzumaki Saburo",
                    notificationTimeStamp = "7h",
                    notificationProfile = R.drawable.logo,
                    notificationCategory = "acceptedFriendRequest",
                ),
                NotificationsTile(
                    notificationId = 4,
                    notificationTitle = "just reply your comment",
                    notificationUsername = "Wi wok de tok",
                    notificationTimeStamp = "8h",
                    notificationProfile = R.drawable.logo,
                    notificationCategory = "commentReply",
                ),
            ),
            onNotificationTileClick = {},
            onSlideToDelete = {},
            onMoreVertClick = {}
        ) 
    }
}