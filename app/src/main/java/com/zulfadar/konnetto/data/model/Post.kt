package com.zulfadar.konnetto.data.model

data class Post(
    val id: Long,
    val username: String,
    val profilePict: Int,
    val caption: String,
    val image: Int?,
    val timestamp: String,
    val comments: List<Comments>? = emptyList(),
    val isLiked: Boolean = false,
    val isSaved: Boolean = false,
)
