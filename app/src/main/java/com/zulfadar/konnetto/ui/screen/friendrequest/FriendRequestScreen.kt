package com.zulfadar.konnetto.ui.screen.friendrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.zulfadar.konnetto.data.model.FriendRequest
import com.zulfadar.konnetto.di.Injection
import com.zulfadar.konnetto.ui.ViewModelFactory
import com.zulfadar.konnetto.ui.common.UiState
import com.zulfadar.konnetto.ui.screen.friendrequest.component.FriendRequestTile
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun FriendRequestScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAccaptClick: () -> Unit,
    onDeclineClick: () -> Unit,
    onDisplaynameClick: () -> Unit,
    viewModel: FriendRequestViewModel = viewModel(
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
                viewModel.getAllFriendRequests()
            }
            is UiState.Success -> {
                FriendRequestContent(
                    modifier = modifier,
                    friendRequests = uiState.data,
                    onBackClick = onBackClick,
                    onAccaptClick = onAccaptClick,
                    onDeclineClick = onDeclineClick,
                    onDisplaynameClick = onDisplaynameClick
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FriendRequestContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAccaptClick: () -> Unit,
    onDeclineClick: () -> Unit,
    onDisplaynameClick: () -> Unit,
    friendRequests: List<FriendRequest>,
) {
    Scaffold(
        modifier = modifier,
        topBar =  {
            FriendRequestTopAppBar(
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            items(friendRequests) { data ->
                if (friendRequests.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
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
                            text = "No Friend Request Yet.",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp)
                        )
                    }
                } else {
                    FriendRequestTile(
                        modifier = modifier,
                        displayname = data.displayname,
                        username = data.username,
                        profilePict = data.profilePict,
                        timeStamp = data.timeStamp,
                        onAccaptClick = onAccaptClick,
                        onDeclineClick = onDeclineClick,
                        onDisplaynameClick = onDisplaynameClick
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FriendRequestTopAppBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back),
                    contentDescription = "back button"
                )
            }
        },
        title = {
            Text("Friend Requests",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun FriendRequestScreenPreview() {
    KonnettoTheme {
        FriendRequestContent(
            onBackClick = {},
            friendRequests = listOf(
                FriendRequest(
                    id = 0,
                    displayname = "wi wok detok",
                    username = "wiwokdetok",
                    profilePict = R.drawable.image_mascot,
                    timeStamp = 12
                )
            ),
            onAccaptClick = {},
            onDeclineClick = {},
            onDisplaynameClick = {}
        )
    }
}