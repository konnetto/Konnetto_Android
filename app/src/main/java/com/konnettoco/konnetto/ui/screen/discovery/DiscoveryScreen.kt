package com.konnettoco.konnetto.ui.screen.discovery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konnettoco.konnetto.data.local.model.MyLibraryItem
import com.konnettoco.konnetto.data.local.model.SugoiPicks
import com.konnettoco.konnetto.di.Injection
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.screen.discovery.component.DiscoveryCardItem
import com.konnettoco.konnetto.ui.screen.discovery.component.SugoiPicksRecomendationCardItem
import com.konnettoco.konnetto.ui.viewModelFactory.DiscoveryViewModelFactory

@Composable
fun DiscoveryScreen(
    modifier: Modifier = Modifier,
    viewModel: DiscoveryViewModel = viewModel(
        factory = DiscoveryViewModelFactory(
            Injection.provideSugoiPicksRepository(),
            Injection.provideLibraryRepository()
        )
    )
) {
    val sugoiPicksState by viewModel.sugoiPicksState.collectAsState(initial = UiState.Loading)
    val animeListState by viewModel.animeListState.collectAsState(initial = UiState.Loading)

    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            DiscoveryTopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            when {
                sugoiPicksState is UiState.Loading || animeListState is UiState.Loading -> {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                sugoiPicksState is UiState.Success || animeListState is UiState.Success -> {
                    val sugoiPicks = (sugoiPicksState as UiState.Success).data
                    val animeList = (animeListState as UiState.Success).data

                    DiscoveryContent(
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = {},
                        sugoiPicks = sugoiPicks,
                        animeList = animeList,
                    )
                }
                sugoiPicksState is UiState.Error || animeListState is UiState.Error -> {
                    //                    val errorMsg = (uiState as UiState.Error).errorMessage
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoveryContent(
    modifier: Modifier = Modifier,
    sugoiPicks: List<SugoiPicks>,
    animeList: List<MyLibraryItem>,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChange: (String) -> Unit
) {
//    DiscoverySearchBar(
//        query = query,
//        onQueryChange = onQueryChange,
//        onSearch = onSearch,
//    )
//    Spacer(Modifier.height(12.dp))
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(160.dp),
//        contentPadding = PaddingValues(
//            bottom = 150.dp,
//            top = 8.dp
//        ),
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        modifier = modifier
//            .fillMaxSize()
//            .padding(horizontal = 12.dp),
//    ) {
//        items(
//            items = animeList,
//            key = { animeList -> animeList.id }
//        ) { data ->
//            DiscoveryCardItem(
//                image = data.image,
//                title = data.title,
//                year = data.year,
//                status = data.status,
//                isAlreadyAdded = false,
//                genres = data.genre
//            )
//        }
//    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            DiscoverySearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
            )
        }
        stickyHeader {

        }
        item {
            SugoiPicksSection(
                sugoiPicks = sugoiPicks
            )
        }
        item {
            AnimeListSection(
                animeList = animeList
            )
        }
    }
}

@Composable
fun SugoiPicksSection(
    modifier: Modifier = Modifier,
    sugoiPicks: List<SugoiPicks>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Sugoi Picks",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "Community favorites",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            items(
                items = sugoiPicks,
                key = { sugoiPicks -> sugoiPicks.id }
            ) { data ->
                SugoiPicksRecomendationCardItem(
                    image = data.posterImage,
                    title = data.title,
                    review = data.caption
                )
            }
        }
    }
}

@Composable
fun AnimeListSection(
    modifier: Modifier = Modifier,
    animeList: List<MyLibraryItem>
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Most popular this year",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            userScrollEnabled = false,
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .height(2000.dp)
        ) {
            items(
                items = animeList,
                key = { animeList -> animeList.id }
            ) { data ->
                DiscoveryCardItem(
                    image = data.image,
                    title = data.title,
                    year = data.year,
                    status = data.status,
                    isAlreadyAdded = false,
                    genres = data.genre
                )
            }
        }
    }
}

@Composable
fun DiscoverySearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceContainerLowest,
                shape = RoundedCornerShape(12.dp)
            )
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                if (query.isEmpty()) {
                    Text(
                        text = "Search anime...",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearch(query)
                        }
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        title = {
            Text(
                text = "Discovery",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun DiscoveryPrev() {
//    KonnettoTheme {
//        DiscoveryScreen()
//    }
//}