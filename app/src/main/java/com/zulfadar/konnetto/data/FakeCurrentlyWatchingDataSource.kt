package com.zulfadar.konnetto.data

import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.CurrentlyWatching

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