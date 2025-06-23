package com.zulfadar.konnetto.ui.screen.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.NotificationsTile
import com.zulfadar.konnetto.di.Injection
import com.zulfadar.konnetto.ui.ViewModelFactory
import com.zulfadar.konnetto.ui.common.UiState
import com.zulfadar.konnetto.ui.screen.notification.component.NotificationTile
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onMoreVertClick: () -> Unit,
    onNotificationTileClick: () -> Unit,
    onSlideToDelete: () -> Unit,
    viewModel: NotificationViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepositoy(),
            Injection.provideCurretnlyWatchingRepository(),
            Injection.provideNotificationsRepository(),
            Injection.provideFriendRequestsRepository()
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

@Composable
fun NotificationPageContent(
    modifier: Modifier = Modifier,
    notifications: List<NotificationsTile>,
    onNotificationTileClick: () -> Unit,
    onMoreVertClick: () -> Unit,
    onSlideToDelete: () -> Unit,
) {
    Scaffold(
        topBar = {
            NotificationTopAppBar(
                onMoreVertClick = onMoreVertClick
            )
        },
        modifier = modifier
    ) { paddingValues ->
        if (notifications.isEmpty()) {
            Text(
                text = "No Post Yet.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues),
            ) {
                items(notifications) { data ->
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