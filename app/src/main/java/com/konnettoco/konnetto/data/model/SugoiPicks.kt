package com.konnettoco.konnetto.data.model

data class SugoiPicks(
    val id: Long,
    val author: User,
    val image: Int?,
    val title: String,
    val posterImage: Int,
    val rating: Double,
    val genres: List<String>,
    val caption: String,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val totalLike: Int,
    val totalComments: Int,
    val totalShare: Int,
    val createdAt: Int,
    val updatedAt: String,
)
