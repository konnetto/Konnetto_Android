package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.data.FakeUserDataSource.currentUserDummy
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy1
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy2
import com.konnettoco.konnetto.data.local.model.SugoiPicks

object FakeSugoiPicksDataSource {
    val dummySugoiPicks = listOf(
        SugoiPicks(
            id = 0,
            author = currentUserDummy,
            image = listOf(
                "https://i.pinimg.com/736x/a9/b7/f0/a9b7f088e0a7cab35863ff7e36664267.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTivHivMZqvE5He6x23DqwldBO--ZLjnGzp_w&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSmTFoXHt0EOGrsYmdRedMTKMi-aA-iL2tvQ&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQY6RHS5A0amYsDLo_1f7WSsAic1OPwyEoANA&s",
                "https://media.tenor.com/BVbJq5a-eloAAAAM/spongebob-goodbye.gif",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKpwvp08my61QbM5e37uFJ0w2Jl-aD2mN-Fg&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRl7ZrQwHSlXKo8en0zYxarllb59PaptS-AYw&s",
                "https://i.pinimg.com/564x/a7/92/66/a7926616eee32d33ce60c04ca21465f3.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV6D4fmxljYS1uPgI3qwcKNIRm0DWQchKisQ&s",
                "https://static.wikia.nocookie.net/gundam/images/f/fa/Gundam_Aerial_Front.png/revision/latest?cb=20220630033122"
            ),
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
            image = listOf(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV6D4fmxljYS1uPgI3qwcKNIRm0DWQchKisQ&s",
                "https://static.wikia.nocookie.net/gundam/images/f/fa/Gundam_Aerial_Front.png/revision/latest?cb=20220630033122"
            ),
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