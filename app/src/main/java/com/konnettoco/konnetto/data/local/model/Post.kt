package com.konnettoco.konnetto.data.local.model

import androidx.compose.runtime.Immutable

@Immutable
data class Post(
    val id: Long,
    val author: User,
    val image: List<String>? = null,
    val caption: String = "",
    val isLiked: Boolean = false,
    val isSaved: Boolean = false,
    val totalLike: Int = 0,
    val totalComments: Int = 0,
    val totalShare: Int = 0,
    val comment: List<Comment>? = null,
    val createdAt: String,
    val updatedAt: String? = null
)


