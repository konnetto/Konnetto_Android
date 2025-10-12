package com.konnettoco.konnetto.ui.screen.commentSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.remote.response.CommentsDataItem
import com.konnettoco.konnetto.ui.common.UiState
import com.konnettoco.konnetto.ui.components.ErrorScreen
import com.konnettoco.konnetto.ui.screen.commentSection.component.CommentContainer
import com.konnettoco.konnetto.ui.screen.commentSection.component.SubCommentContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentSection(
    modifier: Modifier = Modifier,
    postId: String,
    parentCommentId: String,
    commentSheetState: SheetState,
    viewModel: CommentSectionViewModel = viewModel(
        factory = CommentSectionViewModelFactory (postId, parentCommentId)
    ),
    onDismissCommentSheet: () -> Unit
) {
    val parentCommentState by viewModel.parentCommentUiState.collectAsState(initial = UiState.Loading)
//    val childCommentState by viewModel.childCommentUiState.collectAsState(initial = UiState.Loading)
    val childCommentsMap by viewModel.childCommentsMap.collectAsState()
    var commentText by remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = modifier.zIndex(1f),
        sheetState = commentSheetState,
        onDismissRequest = onDismissCommentSheet,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding() ,// Memastikan konten terdorong ke atas saat keyboard muncul
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Comments",
                fontWeight = FontWeight.Bold
            )
            when {
                parentCommentState is UiState.Loading -> {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                parentCommentState is UiState.Error -> {
                    val errorMsg = (parentCommentState as UiState.Error).errorMessage
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorScreen(
                            message = errorMsg,
                            onRetry = {
                                viewModel.getCommentsByPostId()
                            }
                        )
                    }
                }

                parentCommentState is UiState.Success -> {
                    val comments = (parentCommentState as UiState.Success<List<CommentsDataItem>>).data

                    if (comments.isEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No comment yet",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Start a conversation now",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(bottom = 8.dp),
                            verticalArrangement = Arrangement.Top
                        ) {
                            itemsIndexed(comments) { index, comment ->
                                var showReplies by rememberSaveable { mutableStateOf(false) }
                                val replyState = childCommentsMap[comment.id]

                                CommentContainer(
                                    avatar = comment.avatarUrl ?: "",
                                    displayname = comment.displayName ?: "",
                                    timeStamp = comment.createdAt ?: "",
                                    comment = comment.content ?: "",
                                    isLiked = false,
                                    likeCount = comment.likeCount ?: 0,
                                )

                                if (comment.replyCount!! > 0 && showReplies == false) {
                                    Row(
                                        modifier = Modifier.padding(start = 60.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
                                            .clickable {
                                                showReplies = !showReplies
                                                viewModel.getCommentRepliesByCommentId(
                                                    comment.id!!
                                                )
                                            },
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        HorizontalDivider(
                                            modifier = Modifier.width(100.dp),
                                            thickness = 2.dp
                                        )
                                        Text(
                                            text = "View ${comment.replyCount} replies",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        HorizontalDivider(
                                            modifier = Modifier.width(100.dp),
                                            thickness = 2.dp
                                        )
                                    }
                                }

                                if (showReplies) {
                                    when (replyState) {
                                        is UiState.Loading -> {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(start = 80.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                Text(
                                                    text = "Loading...",
                                                    fontSize = 12.sp,
                                                    color = Color.Gray
                                                )
                                            }
                                        }

                                        is UiState.Error -> {
                                            ErrorScreen(
                                                message = replyState.errorMessage,
                                                onRetry = {
                                                    viewModel.getCommentRepliesByCommentId(comment.id!!)
                                                }
                                            )
                                        }

                                        is UiState.Success -> {
                                            val replies = replyState.data
                                            Column(modifier = Modifier.padding(start = 48.dp)) {
                                                replies.forEach { reply ->
                                                    SubCommentContainer(
                                                        avatar = reply.avatar ?: "",
                                                        displayname = reply.displayName ?: "",
                                                        createdAt = reply.createdAt ?: "",
                                                        comment = reply.content ?: "",
                                                        isLiked = false,
                                                        likeCount = reply.likeCount ?: 0
                                                    )
                                                }
                                                Row(
                                                    modifier = Modifier.padding(start = 60.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
                                                        .clickable {
                                                            showReplies = !showReplies
                                                        },
                                                    horizontalArrangement = Arrangement.Center,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        text = "Hide replies",
                                                        fontSize = 12.sp,
                                                        color = Color.Gray
                                                    )
                                                }
                                            }
                                        }

                                        else -> {

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
//            val contentCount = 0
//            if (contentCount == 0) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "No comment yet",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Text(
//                        text = "Start a conversation now",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Normal
//                    )
//                }
//            } else {
//                LazyColumn(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxWidth(),
//                    contentPadding = PaddingValues(bottom = 8.dp),
//                    verticalArrangement = Arrangement.Top,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    items(contentCount, key = { it }) { index ->
//                        CommentContainer(
//                            displayname = "Zul",
//                            timeStamp = "2020-10-03T00:00:00Z",
//                            comment = "Halo selamat pagi dunia!!",
//                            isLiked = false,
//                            likeCount = 1,
//                            replyCount = 0
//                        )
//                    }
//                }
//            }
            // TextField + Tombol Kirim
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                OutlinedTextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    placeholder = { Text("Add a comment...") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp),
                    shape = RoundedCornerShape(24.dp),
                    maxLines = 4,
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp // opsional
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
                    ),
                )
                IconButton(
                    onClick = {
//                        if (commentText.isNotBlank()) {
//                            println("Comment sent: $commentText")
//                            commentText = ""
//                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Send, // Ganti sesuai iconmu
                        contentDescription = "Send Comment",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}