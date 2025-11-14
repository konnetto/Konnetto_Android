package com.konnettoco.konnetto.data.local.model

data class Comment(
    val id: Long,
    val postId: Long,
    val parentId: Long? = null,
    val path: String = "", // ex: "1/5/8"
    val author: User,
    val caption: String = "",
    val image: String? = null,
    val pinned: Boolean = false,
    val isLiked: Boolean = false,
    val totalLike: Int = 0,
    val totalReplies: Int = 0,
    val totalLoadedReplies: Int = 0,
    val replyComment: List<Comment> = emptyList(),
    val createdAt: String = ""
)
