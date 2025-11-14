package com.konnettoco.konnetto.ui.screen.home

import com.konnettoco.konnetto.data.local.model.Post
import com.konnettoco.konnetto.ui.common.UiState

data class HomeUiState(
    val selectedTabIndex: Int = 0,
    val forYouPosts: UiState<List<Post>> = UiState.Loading,
    val friendsPosts: UiState<List<Post>> = UiState.Loading,
    val sugoiPosts: UiState<List<Post>> = UiState.Loading,
    val isRefreshing: Boolean = false,
    val showCommentSheet: Boolean = false,
    val showLikedBySheet: Boolean = false,
)
