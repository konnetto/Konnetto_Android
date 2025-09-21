package com.konnettoco.konnetto.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun formatDateTime(dateTimeString: String): String {
    // Parsing to Instant (UTC)
    val instant = Instant.parse(dateTimeString)

    // Convert to local datetime
    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    val now = LocalDateTime.now()
    val duration = java.time.Duration.between(dateTime, now)

    return when {
        duration.toMinutes() < 1 -> "just now"
        duration.toMinutes() < 60 -> "${duration.toMinutes()} minute ago"
        duration.toHours() < 24 -> "${duration.toHours()} hour ago"
        duration.toDays() < 7 -> "${duration.toDays()} days ago"
        dateTime.year == now.year -> dateTime.format(DateTimeFormatter.ofPattern("d MMM"))
        else -> dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
    }
}

