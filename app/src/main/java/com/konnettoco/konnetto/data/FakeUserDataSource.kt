package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.model.User

object FakeUserDataSource {
    val currentUserDummy = User(
        id = 0,
        username = "charaznable08",
        displayname = "Char Aznable",
        email = "charaznable@email.com",
        isCurrentUser = true,
        isCreator = false,
        photo = R.drawable.logo,
        bio = "Lagi Batuk",
        totalFriends = 2,
        totalFollowing = 230,
        totalPost = 12,
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
        photo = R.drawable.memespongebob,
        bio = "Hallo",
        totalFriends = 23,
        totalFollowing = 0,
        totalPost = 12,
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
        photo = R.drawable.logo,
        bio = "Awikwokawokawok",
        totalFriends = 23,
        totalFollowing = 0,
        totalPost = 12,
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
        photo = R.drawable.logo,
        bio = "Kok iso",
        totalFriends = 23,
        totalFollowing = 0,
        totalPost = 12,
        friend = false,
        followed = false,
        createdAt = "23 December 2024",
        updatedAt = "25 December 2024"
    )
}