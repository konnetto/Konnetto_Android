package com.zulfadar.konnetto.data.model

data class Post(
    val id: Long,
    val displayname: String,
    val username: String,
    val profilePict: Int,
    val caption: String,
    val image: Int?,
    val timestamp: String,
    val comments: List<Comment>? = emptyList(),
    val isLiked: Boolean = false,
    val isSaved: Boolean = false,
)

//data class Post(
//    val id: Long,
//    val author: UserProfile,
//    val image: Int?,
//    val caption: String,
//    val isLiked: Boolean,
//    val isSaved: Boolean,
//    val totalLike: Int,
//    val totalComments: Int,
//    val totalShare: Int,
//    val createdAt: String,
//    val updatedAt: String,
//)


