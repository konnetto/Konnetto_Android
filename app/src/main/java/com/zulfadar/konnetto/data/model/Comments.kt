package com.zulfadar.konnetto.data.model

data class Comments(
    val id: String,
    val userId: String,
    val content: String,
    val timestamp: Long,
    val replies: List<Comments> = emptyList() // Rekursif untuk nested comments
)
