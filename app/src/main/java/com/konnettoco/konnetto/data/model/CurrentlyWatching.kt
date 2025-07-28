package com.konnettoco.konnetto.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class CurrentlyWatching(
    val id: Long,
    val title: String,
    val poster: Int,
)
