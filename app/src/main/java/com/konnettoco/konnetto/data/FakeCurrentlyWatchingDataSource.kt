package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.model.CurrentlyWatching

object FakeCurrentlyWatchingDataSource {
    val dummyCurrentlyWatching = listOf(
        CurrentlyWatching(
            id = 1,
            title = "Sword Art Online",
            poster = R.drawable.memespongebob
        ),
        CurrentlyWatching(
            id = 2,
            title = "Sword Art Online",
            poster = R.drawable.memespongebob
        ),
        CurrentlyWatching(
            id = 3,
            title = "Sword Art Online",
            poster = R.drawable.memespongebob
        ),
        CurrentlyWatching(
            id = 4,
            title = "Sword Art Online",
            poster = R.drawable.memespongebob
        ),
    )
}