package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.data.FakeUserDataSource.currentUserDummy
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy1
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy2
import com.konnettoco.konnetto.data.model.SugoiPicks

object FakeSugoiPicksDataSource {
    val dummySugoiPicks = listOf(
        SugoiPicks(
            id = 0,
            author = currentUserDummy,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV6D4fmxljYS1uPgI3qwcKNIRm0DWQchKisQ&s",
            title = "Mobile Suit Gundam GQuuuuuuux",
            posterImage = "https://m.media-amazon.com/images/M/MV5BNWZkMzEzMzQtNzQwOS00OThjLWFmYWQtMGNmMzllNTUwMDQ5XkEyXkFqcGc@._V1_.jpg",
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Romance", "Drama", "Crossover"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = "2025-09-19T22:10:00Z",
            updatedAt = "2025-09-19T22:10:00Z"
        ),
        SugoiPicks(
            id = 1,
            author = otherUserDummy1,
            image = null,
            title = "Mobile Suit Gundam Unicorn",
            posterImage = "https://img.sunrise-inc.co.jp/images/datacard/379_main.jpg",
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Action"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = "2025-09-18T22:10:00Z",
            updatedAt = "2025-09-18T22:10:00Z"
        ),
        SugoiPicks(
            id = 2,
            author = otherUserDummy2,
            image = "https://i.pinimg.com/564x/67/b9/4a/67b94abda90218b077da5762a0df0d2c.jpg",
            title = "Mobile Suit Gundam Stardust Memory",
            posterImage = "https://m.media-amazon.com/images/M/MV5BYjAyMjMzYTQtZGNmOC00YjMzLTgzYjMtM2U0MTUzNzNjODljXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Drama"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = "2025-08-19T22:10:00Z",
            updatedAt = "2025-08-19T22:10:00Z"
        ),
        SugoiPicks(
            id = 3,
            author = otherUserDummy2,
            image = null,
            title = "Mobile Suit Gundam Seed",
            posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4F0_CE8RHxGa2rOoNbCOlViUEdELUK83t8Q&s",
            rating = 8.9,
            genres = listOf("Military", "Mecha", "Romance", "Drama"),
            caption = "Gokil awokawok",
            isLiked = true,
            isSaved = true,
            totalLike = 200,
            totalComments = 30,
            totalShare = 23,
            createdAt = "2023-08-19T22:10:00Z",
            updatedAt = "2023-08-19T22:10:00Z"
        )
    )
}