package com.konnettoco.konnetto.utils

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun formatDateTime(dateTimeString: String): String {
    val instant = try {
        // Tambah Z kalau tidak ada timezone
        val normalized = if (!dateTimeString.endsWith("Z") && !dateTimeString.contains("+")) {
            "${dateTimeString}Z"
        } else dateTimeString

        Instant.parse(normalized)
    } catch (e: Exception) {
        // fallback: parse manual tanpa timezone
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dateTimeString, formatter)
        localDateTime.atZone(ZoneId.systemDefault()).toInstant()
    }

    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val now = LocalDateTime.now()
    val duration = Duration.between(dateTime, now)

    return when {
        duration.toMinutes() < 1 -> "just now"
        duration.toMinutes() < 60 -> "${duration.toMinutes()} minute${if (duration.toMinutes() > 1) "s" else ""} ago"
        duration.toHours() < 24 -> "${duration.toHours()} hour${if (duration.toHours() > 1) "s" else ""} ago"
        duration.toDays() < 7 -> "${duration.toDays()} day${if (duration.toDays() > 1) "s" else ""} ago"
        dateTime.year == now.year -> dateTime.format(DateTimeFormatter.ofPattern("d MMM"))
        else -> dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
    }
}

