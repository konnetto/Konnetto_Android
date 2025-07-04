package com.zulfadar.konnetto.ui.screen.otppages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyTabRow() {
    val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val listState = rememberLazyListState()
    val isTabRowSticky = remember { mutableStateOf(true) }

    // Menentukan apakah TabRow harus sticky berdasarkan posisi scroll
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { firstVisibleItemIndex ->
                isTabRowSticky.value = firstVisibleItemIndex == 0
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            // Header (TabRow)
            if (isTabRowSticky.value) {
                stickyHeader {
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = { Text(title) }
                            )
                        }
                    }
                }
            }


            // Konten
            items(100) { index ->
                if (index == 0 && !isTabRowSticky.value) {
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabTitles.forEachIndexed { tabIndex, title ->
                            Tab(
                                selected = selectedTabIndex == tabIndex,
                                onClick = { selectedTabIndex = tabIndex },
                                text = { Text(title) }
                            )
                        }
                    }
                }
                Text(text = "Item $index")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StickyTablayoutPrev() {
    StickyTabRow()
}
