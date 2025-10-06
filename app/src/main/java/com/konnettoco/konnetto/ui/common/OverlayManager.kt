package com.konnettoco.konnetto.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.konnettoco.konnetto.ui.screen.commentSection.CommentSection
import com.konnettoco.konnetto.ui.screen.likedby.LikedBySection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverlayManager(
    showCommentSectionSheet: Boolean,
    selectedPostId: String?,
    showLikedBySectionSHeet: Boolean,
    onDismissCommentSheet: () -> Unit,
    onDismissLikedBySheet: () -> Unit,
) {
    val commentSectionState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val likedBySectionState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val coroutineScope = rememberCoroutineScope()

    if (showCommentSectionSheet && selectedPostId != null) {
        CommentSection(
            postId = selectedPostId,
            commentSheetState = commentSectionState,
            onDismissCommentSheet = {
                coroutineScope.launch {
                    commentSectionState.hide()
                    onDismissCommentSheet()
                }
            }
        )
    }
//    if (showCommentSectionSheet) {
//        CommentSection(
//            commentSheetState = commentSectionState,
//            onDismissCommentSheet = {
//                coroutineScope.launch {
//                    commentSectionState.hide()
//                    onDismissCommentSheet()
//                }
//            }
//        )
//    }

    if (showLikedBySectionSHeet) {
        LikedBySection(
            likedBySheetState = likedBySectionState,
            onDismissLikedBySheet = {
                coroutineScope.launch {
                    likedBySectionState.hide()
                    onDismissLikedBySheet()
                }
            },
            query = "",
            onQueryChange = {}
        )
    }

    // Tambahkan overlay lain di sini nanti jika diperlukan
    // e.g. ShareDialog(), ReportBottomSheet(), dll
}