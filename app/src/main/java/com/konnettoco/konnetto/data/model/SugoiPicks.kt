package com.konnettoco.konnetto.data.model

data class SugoiPicks(
    val id: Long,
    val author: UserProfile,
    val image: Int?,
    val title: String,
    val rating: Double,
    val status: String,
    val genre: String,
    val episodes: Int?,
    val caption: String,
    val stared: Boolean,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val totalLike: Int,
    val totalComments: Int,
    val totalShare: Int,
    val createdAt: String,
    val updatedAt: String,
)
