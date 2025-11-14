package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.data.local.model.User

object FakeUserDataSource {
    val currentUserDummy = User(
        id = 0,
        username = "charaznable08",
        displayname = "Char Aznable",
        email = "charaznable@email.com",
        isCurrentUser = true,
        isCreator = false,
        isFriend = true,
        photo = "https://imageio.forbes.com/specials-images/imageserve//62ab33637e602563e78819cd/0x0.jpg?format=jpg&height=900&width=1600&fit=bounds",
        bio = "Lagi Batuk",
        totalFriends = 2,
        totalFollowing = 230,
        totalPost = 12,
        totalWatch = 6,
        friend = false,
        followed = false,
        createdAt = "23 December 2024",
        updatedAt = "25 December 2024"
    )

    val otherUserDummy1 = User(
        id = 1,
        username = "comolicute",
        displayname = "Comoli Harcourt",
        email = "comoliiscute@email.com",
        isCurrentUser = false,
        isCreator = false,
        isFriend = true,
        photo = "https://en.gundam.info/about-gundam/series-pages/gquuuuuux/glh/jp/character/2025/05/chara_06_01.png",
        bio = "Hallo",
        totalFriends = 23,
        totalFollowing = 0,
        totalPost = 12,
        totalWatch = 4,
        friend = true,
        followed = false,
        createdAt = "23 December 2024",
        updatedAt = "25 December 2024"
    )

    val otherUserDummy2 =  User(
        id = 2,
        username = "gugum08",
        displayname = "Gugum Gugum 08",
        email = "gugum08@email.com",
        isCurrentUser = false,
        isCreator = false,
        isFriend = false,
        photo = "https://cdn.shopify.com/s/files/1/0573/7569/files/hall_monitor_125_1000x.jpg?v=1724059545",
        bio = "Awikwokawokawok",
        totalFriends = 23,
        totalFollowing = 0,
        totalPost = 12,
        totalWatch = 6,
        friend = false,
        followed = false,
        createdAt = "23 December 2024",
        updatedAt = "25 December 2024"
    )

    val otherUserDummy3 =  User(
        id = 3,
        username = "amurock",
        displayname = "Amuro Ray",
        email = "amurock@email.com",
        isCurrentUser = false,
        isCreator = false,
        isFriend = false,
        photo = "https://static.wikia.nocookie.net/gundam/images/9/95/AmuroRay.jpg/revision/latest?cb=20070528042538",
        bio = "Kok iso",
        totalFriends = 23,
        totalFollowing = 0,
        totalPost = 12,
        totalWatch = 6,
        friend = false,
        followed = false,
        createdAt = "23 December 2024",
        updatedAt = "25 December 2024"
    )
}