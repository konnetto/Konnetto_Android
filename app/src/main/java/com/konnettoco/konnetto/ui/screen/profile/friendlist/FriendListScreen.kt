package com.konnettoco.konnetto.ui.screen.profile.friendlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.model.FriendList
import com.konnettoco.konnetto.di.Injection
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.screen.profile.friendlist.component.FriendTile
import com.konnettoco.konnetto.ui.viewModelFactory.FriendListViewModelFactory

@Composable
fun FriendListScreen(
    modifier: Modifier = Modifier,
    viewModel: FriendListViewModel = viewModel(
        factory = FriendListViewModelFactory(
            Injection.provideFriendListRepository()
        )
    ),
    navigateToProfile: () -> Unit,
    onBackClick: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is  UiState.Loading -> {
                viewModel.getAllFriendList()
            }
            is UiState.Success -> {
                FriendListContent(
                    modifier = modifier,
                    friendList = uiState.data,
                    onBackClick = onBackClick
                ) 
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FriendListContent(
    modifier: Modifier = Modifier,
    friendList: List<FriendList>?,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            FriendListTopBar(
                onBackClick = onBackClick
            )
        }
    ) { innerPadding -> 
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (friendList.isNullOrEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No Friend Yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 12.dp)
                ) {
                    items(
                        items = friendList,
                        key = { friend -> friend.id } // <- gunakan ID unik dari post
                    ) { data ->
                        FriendTile(
                            onFriendTileClick = {},
                            profilePict = data.profilePict,
                            displayname = data.displayname,
                            username = data.username
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendListTopBar(
    onBackClick: () -> Unit
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
                    contentDescription = "back button"
                )
            }
        },
        title = {
            Text(
                "Friend List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun FriendListPreview() {
    FriendListContent(
        onBackClick = {},
        friendList = listOf(
            FriendList(
                id = 0,
                displayname = "Uzumaki Saburo",
                profilePict = R.drawable.logo,
                username = "saburo"
            ),
            FriendList(
                id = 1,
                displayname = "Kaoruko Waguri",
                profilePict = null,
                username = "waguri"
            ),
            FriendList(
                id = 2,
                displayname = "Comoli Hartcourt",
                profilePict = null,
                username = "comoli"
            ),
        )
    )
}