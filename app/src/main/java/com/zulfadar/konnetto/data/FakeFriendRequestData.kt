package com.zulfadar.konnetto.data

import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.FriendRequest

object FakeFriendRequestData {
    val dummyFriendRequestData = listOf(
        FriendRequest(
            id = 0,
            displayname = "Fufufafa",
            username = "fufufafa",
            profilePict = R.drawable.image_mascot,
            timeStamp = 12
        ),
        FriendRequest(
            id = 1,
            displayname = "Wiwok Detok",
            username = "wiwokdetok123",
            profilePict = R.drawable.logo,
            timeStamp = 12,
        ),
        FriendRequest(
            id = 2,
            displayname = "Wok wok",
            username = "omonomon12",
            profilePict = R.drawable.image_mascot,
            timeStamp = 12
        ),
        FriendRequest(
            id = 3,
            displayname = "Tung tung tung sahur",
            username = "tungtung",
            profilePict = R.drawable.image_mascot,
            timeStamp = 12
        ),
        FriendRequest(
            id = 4,
            displayname = "Muhammad Sumbul",
            username = "sumbul00",
            profilePict = R.drawable.logo,
            timeStamp = 12
        ),
    )
}