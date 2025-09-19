package com.konnettoco.konnetto.data.model

import androidx.compose.runtime.Immutable

//data class Post(
//    val id: Long,
//    val displayname: String,
//    val username: String,
//    val profilePict: Int,
//    val caption: String,
//    val image: Int?,
//    val timestamp: String,
//    val comments: List<Comment>? = emptyList(),
//    val isLiked: Boolean = false,
//    val isSaved: Boolean = false,
//)

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
    val comment: List<Comment> = emptyList(),
    val createdAt: Int,
    val updatedAt: String? = null
)


