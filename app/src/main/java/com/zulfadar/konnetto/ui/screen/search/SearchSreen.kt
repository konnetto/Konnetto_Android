package com.zulfadar.konnetto.ui.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.screen.search.component.HistorySearchTile

@Composable
fun SearchPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    val searchHistory = remember { mutableStateListOf("One Piece", "Naruto", "Attack on Titan") }
    var query by remember { mutableStateOf("") }

    SearchPageContent(
        modifier = modifier,
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            if (it.isNotBlank() && it !in searchHistory) {
                searchHistory.add(0, it)
            }
        },
        searchHistory = searchHistory,
        onBackClick = onBackClick
    )
}

@Composable
fun SearchPageContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    searchHistory: List<String>,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchPageTopBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(searchHistory) { item ->
                HistorySearchTile(
                    historySearch = item,
                    onDeleteClick = {},
                    onHistorySearchClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchPageTopBar(
    onBackClick: () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back),
                    contentDescription = "back button"
                )
            }
        },
        title = {
            CustomSearchBar(
                query = query,
                onQueryChange = onQueryChange,
            )
        },
        actions = {
            TextButton(onClick = { onSearch(query) }) {
                Text(
                    text = "Search",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(36.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest, shape = RoundedCornerShape(20.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (query.isEmpty()) {
            Text("Search...", fontSize = 14.sp, color = Color.Gray)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                textStyle = TextStyle(fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface),
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                modifier = Modifier.weight(1f)
            )
            // Trailing Icon
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") },
                    modifier = Modifier
                        .size(16.dp)
                        .padding(0.dp)
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

@Preview
@Composable
private fun SearchPagePreview() {
    SearchPageContent(
        onBackClick = {},
        query = "",
        onQueryChange = {},
        onSearch = {},
        searchHistory = listOf(
            "One Piece",
            "Naruto",
            "Gundam",
        )
    )
}