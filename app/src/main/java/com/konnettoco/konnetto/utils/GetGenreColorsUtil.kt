package com.konnettoco.konnetto.utils

import androidx.compose.ui.graphics.Color

fun getGenreColor(genre: String): Pair<Color, Color> {
    val colors = listOf(
        Pair(Color(0xFFFFCEAD), Color(0xFFFF6E00)),
        Pair(Color(0xFFFFB9C8), Color(0xFFFF003B)),
        Pair(Color(0xFFB0B7FF), Color(0xFF001AFF)),
        Pair(Color(0xFFDC80FF), Color(0xFF7A0087)),
        Pair(Color(0xFFE4C6FF), Color(0xFF6A00FF)),
        Pair(Color(0xFFFFF5B7), Color(0xFFF5A300)),
    )
    val index = (genre.hashCode() and 0xFFFFFFF) % colors.size
    return colors[index]
}