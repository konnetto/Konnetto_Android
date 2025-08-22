package com.konnettoco.konnetto.ui.screen.library.mylibrary

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.model.MyLibraryItem
import com.konnettoco.konnetto.di.Injection
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.navigation.TabItem
import com.konnettoco.konnetto.ui.screen.library.mylibrary.component.LibraryItemCard
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import com.konnettoco.konnetto.ui.viewModelFactory.LibraryViewModelVictory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LibraryPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit,
    viewModel: LibraryViewModel = viewModel(
        factory = LibraryViewModelVictory(
            Injection.provideLibraryRepository()
        )
    )
) {
    val libraryState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    val tabItems = listOf(
        TabItem(title = "Watching"),
        TabItem(title = "Completed"),
        TabItem(title = "Plan to watch"),
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
//    Column(
//        modifier = modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            modifier = Modifier
//                .size(100.dp)
//                .clip(CircleShape),
//            painter = painterResource(R.drawable.image_mascot),
//            contentDescription = null
//        )
//        Text(
//            text = "Library Picks coming soon! Stay tuned for amazing contents.",
//            style = MaterialTheme.typography.bodyMedium,
//            textAlign = TextAlign.Justify,
//            modifier = Modifier.padding(horizontal = 100.dp, vertical = 12.dp),
//            color = MaterialTheme.colorScheme.onSurface
//        )
//    }
    Scaffold(
        modifier = modifier,
        topBar = {
            LibraryPageTopBar(
                onBackClick = onBackClick,
                onMoreVertClick = onMoreVertClick
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LibraryTabs(
                tabItems = tabItems,
                pagerState = pagerState,
                tabCoroutineScope = coroutineScope,
                selectedTabIndex = selectedTabIndex
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { index ->
                when(index) {
                    0 -> {
                        when {
                            libraryState is UiState.Loading -> {
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            libraryState is UiState.Success -> {
                                if (libraryState is UiState.Success) {
                                    val myLibrary = (libraryState as UiState.Success).data
                                    val watchingList = myLibrary.filter { item ->
                                        item.currentEpisode > 0 && item.currentEpisode < item.totalEpisode
                                    }
                                    WatchingContent(
                                        modifier = modifier,
                                        myLibrary = watchingList
                                    )
                                }
                            }

                            libraryState is UiState.Error -> {
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
                        when {
                            libraryState is UiState.Loading -> {
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            libraryState is UiState.Success -> {
                                if (libraryState is UiState.Success) {
                                    val myLibrary = (libraryState as UiState.Success).data
                                    val completedList = myLibrary.filter { item ->
                                        item.currentEpisode == item.totalEpisode && item.totalEpisode > 0
                                    }
                                    CompletedContent(
                                        modifier = modifier,
                                        myLibrary = completedList
                                    )
                                }
                            }

                            libraryState is UiState.Error -> {
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
                    2 -> {
                        when {
                            libraryState is UiState.Loading -> {
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            libraryState is UiState.Success -> {
                                if (libraryState is UiState.Success) {
                                    val myLibrary = (libraryState as UiState.Success).data
                                    val planToWatchList = myLibrary.filter { item ->
                                        item.currentEpisode == 0
                                    }
                                    PlantToWatchContent(
                                        modifier = modifier,
                                        myLibrary = planToWatchList
                                    )
                                }
                            }

                            libraryState is UiState.Error -> {
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
fun WatchingContent(
    modifier: Modifier = Modifier,
    myLibrary: List<MyLibraryItem>,
) {
    if (myLibrary.isNullOrEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.image_mascot),
                contentDescription = null
            )
            Text(
                text = "You are not watching anything yet.",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 80.dp, vertical = 12.dp),
                color = MaterialTheme.colorScheme.onSurface
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
                items = myLibrary,
                key = { mylibrary -> mylibrary.id }
            ) { data ->
                LibraryItemCard(
                    image = data.image,
                    title = data.title,
                    synopsis = data.synopsis,
                    genre = data.genre,
                    rating = data.rating,
                    studio = data.studio,
                    currentEpisode = data.currentEpisode,
                    totalEpisode = data.totalEpisode,
                    onItemClick = {}
                )
            }
        }
    }
}

@Composable
fun CompletedContent(
    modifier: Modifier = Modifier,
    myLibrary: List<MyLibraryItem>,
) {
    if (myLibrary.isNullOrEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.image_mascot),
                contentDescription = null
            )
            Text(
                text = "You are not complete anything yet.",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 80.dp, vertical = 12.dp),
                color = MaterialTheme.colorScheme.onSurface
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
                items = myLibrary,
                key = { mylibrary -> mylibrary.id }
            ) { data ->
                LibraryItemCard(
                    image = data.image,
                    title = data.title,
                    synopsis = data.synopsis,
                    genre = data.genre,
                    rating = data.rating,
                    studio = data.studio,
                    currentEpisode = data.currentEpisode,
                    totalEpisode = data.totalEpisode,
                    onItemClick = {}
                )
            }
        }
    }
}

@Composable
fun PlantToWatchContent(
    modifier: Modifier = Modifier,
    myLibrary: List<MyLibraryItem>,
) {
    if (myLibrary.isNullOrEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.image_mascot),
                contentDescription = null
            )
            Text(
                text = "There is no plan yet.",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 80.dp, vertical = 12.dp),
                color = MaterialTheme.colorScheme.onSurface
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
                items = myLibrary,
                key = { mylibrary -> mylibrary.id }
            ) { data ->
                LibraryItemCard(
                    image = data.image,
                    title = data.title,
                    synopsis = data.synopsis,
                    genre = data.genre,
                    rating = data.rating,
                    studio = data.studio,
                    currentEpisode = data.currentEpisode,
                    totalEpisode = data.totalEpisode,
                    onItemClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LibraryTabs(
    modifier: Modifier = Modifier,
    tabItems: List<TabItem>,
    pagerState: PagerState,
    tabCoroutineScope: CoroutineScope,
    selectedTabIndex: Int,
) {
    Box(
        modifier = modifier
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
                        .background(if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.Transparent),
                    selected = index == selectedTabIndex,
                    selectedContentColor = Color.Transparent,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                    onClick = {
                        // Saat klik tab, scroll ke page tanpa trigger konflik dari swipe
                        tabCoroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = item.title,
                            color = if (selectedTabIndex == index) Color.White else MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryPageTopBar(
    onBackClick: () -> Unit,
    onMoreVertClick: () -> Unit
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
                    contentDescription = "back button",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Your Library",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(
                onClick = onMoreVertClick,
                enabled = false,
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more option",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LibraryPageScreenPreview() {
    KonnettoTheme {
        LibraryPageScreen(
            onBackClick = {},
            onMoreVertClick = {}
        )
    }
}

@Preview(showBackground = true,  uiMode = Configuration.UI_MODE_NIGHT_YES,)
@Composable
private fun LibraryPageScreenDarkPreview() {
    KonnettoTheme {
        LibraryPageScreen(
            onBackClick = {},
            onMoreVertClick = {}
        )
    }
}