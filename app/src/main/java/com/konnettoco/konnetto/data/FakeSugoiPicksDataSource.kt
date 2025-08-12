package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.FakeUserDataSource.currentUserDummy
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy1
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy2
import com.konnettoco.konnetto.data.model.SugoiPicks

object FakeSugoiPicksDataSource {
    val dummySugoiPicks = listOf(
        SugoiPicks(
            id = 0,
            author = currentUserDummy,
            image = R.drawable.memespongebob,
            title = "Mobile Suit Gundam GQuuuuuuux",
            posterImage = R.drawable.memespongebob,
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Romance", "Drama", "Crossover"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = 12,
            updatedAt = ""
        ),
        SugoiPicks(
            id = 1,
            author = otherUserDummy1,
            image = null,
            title = "Mobile Suit Gundam Unicorn",
            posterImage = R.drawable.memespongebob,
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Action"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = 12,
            updatedAt = ""
        ),
        SugoiPicks(
            id = 2,
            author = otherUserDummy2,
            image = R.drawable.memespongebob,
            title = "Mobile Suit Gundam Stardust Memory",
            posterImage = R.drawable.memespongebob,
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Drama"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = 12,
            updatedAt = ""
        ),
        SugoiPicks(
            id = 3,
            author = otherUserDummy2,
            image = null,
            title = "Mobile Suit Gundam Seed",
            posterImage = R.drawable.memespongebob,
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Romance", "Drama"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = 12,
            updatedAt = ""
        )
    )
}