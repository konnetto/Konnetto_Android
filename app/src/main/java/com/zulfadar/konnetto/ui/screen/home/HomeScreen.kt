package com.zulfadar.konnetto.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.Post
import com.zulfadar.konnetto.data.model.Posting
import com.zulfadar.konnetto.di.Injection
import com.zulfadar.konnetto.ui.ViewModelFactory
import com.zulfadar.konnetto.ui.common.UiState
import com.zulfadar.konnetto.ui.components.PostCardItem
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepositoy())
    ),
    navigateToComment: () -> Unit,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit,
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
                    onProfileClick = onProfileClick
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
    postings: List<Posting>,
    navigateToComments: () -> Unit,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) { paddingValues ->
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(weight = 1f).padding(paddingValues)
            ) {
                items(postings) { data ->
                    PostCardItem(
                        username = data.posting.username,
                        timestamp = data.posting.timestamp,
                        profilePict = data.posting.profilePict,
                        image = data.posting.image,
                        caption = data.posting.caption,
                        onCommentsClick = navigateToComments,
                        modifier = modifier,
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar(
    onMenuClick: () -> Unit,
    onProfileClick: ()-> Unit
) {
    TopAppBar(
//        navigationIcon = {
//            IconButton(onClick = {
//                onMenuClick()
//            }) {
//                Icon(
//                    imageVector = Icons.Default.Menu,
//                    contentDescription = "drawer menu"
//                )
//            }
//        },
        title = {
            Text("Konnetto",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = onProfileClick ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(onClick = onProfileClick ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .clickable { onProfileClick() }
                )
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    KonnettoTheme {
        HomeContent(
            postings = listOf(
                Posting(
                    posting = Post(
                        id = 0,
                        username = "Char Aznable",
                        profilePict = R.drawable.logo,
                        caption = "Awikwok Test",
                        image = R.drawable.memespongebob,
                        timestamp = "16 h",
                        comments = null,
                        isLiked = false,
                        isSaved = false,
                    )
                ),
                Posting(
                    posting = Post(
                        id = 1,
                        username = "Char Aznable",
                        profilePict = R.drawable.logo,
                        caption = "Awikwok Test",
                        image = null,
                        timestamp = "16 h",
                        comments = null,
                        isLiked = false,
                        isSaved = false,
                    )
                ),
                Posting(
                    posting = Post(
                        id = 2,
                        username = "Char Aznable",
                        profilePict = R.drawable.logo,
                        caption = "Awikwok Test",
                        image = R.drawable.memespongebob,
                        timestamp = "16 h",
                        comments = null,
                        isLiked = false,
                        isSaved = false,
                    )
                )
            ),
            navigateToComments = {},
            onMenuClick = {},
            onProfileClick = {}
        )
    }
}