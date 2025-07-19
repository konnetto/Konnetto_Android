package com.konnettoco.konnetto.utils

fun Int.formatCount(): String {
    return when {
        this >= 1000000 -> "${this / 1000000}M"
        this >= 10000 -> "${this / 1000}k"
        else -> this.toString()
    }
}