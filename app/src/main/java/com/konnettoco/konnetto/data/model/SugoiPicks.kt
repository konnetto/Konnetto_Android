package com.konnettoco.konnetto.data.model

data class SugoiPicks(
    val id: Long,
    val author: User,
    val image: String?,
    val title: String,
    val posterImage: String,
    val rating: Double,
    val genres: List<String>,
    val caption: String,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val totalLike: Int,
    val totalComments: Int,
    val totalShare: Int,
    val createdAt: String,
    val updatedAt: String,
)
