package com.zulfadar.konnetto.data.model

import java.time.LocalDateTime

data class Comment(
    val id: Long,
    val postId: Long,
    val parentId: Long?,
    val path: String,
    val author: UserProfile,
    val caption: String,
    val image: String?,
    val pinned: Boolean,
    val isLiked: Boolean,
    val totalLike: Int,
    val totalReplies: Int,
    val totalLoadedReplies: Int,
    val createdAt: LocalDateTime
) {
    val pathSize: Int
        get() = path.split(".").size
}
