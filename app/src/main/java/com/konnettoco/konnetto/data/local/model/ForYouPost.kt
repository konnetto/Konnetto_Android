package com.konnettoco.konnetto.data.local.model

data class ForYouPost(
    val id: Long,
    val author: UserProfile,
    val image: Int?,
    val caption: String,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val totalLike: Int,
    val totalComments: Int,
    val totalShare: Int,
    val createdAt: String,
    val updatedAt: String,
)
