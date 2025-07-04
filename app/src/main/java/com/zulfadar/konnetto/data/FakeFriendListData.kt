package com.zulfadar.konnetto.data

import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.data.model.FriendList

object FakeFriendListData {
    val dummyFriendListData = listOf(
        FriendList(
            id = 0,
            displayname = "Uzumaki Boruto",
            profilePict = R.drawable.logo,
            username = "borutod",
        ),
        FriendList(
            id = 1,
            displayname = "Kaoruko Waguri",
            profilePict = null,
            username = "waguri",
        ),
        FriendList(
            id = 2,
            displayname = "Prabroro",
            profilePict = null,
            username = "prabski77",
        ),
        FriendList(
            id = 3,
            displayname = "Comoli Hartcourt",
            profilePict = R.drawable.img,
            username = "comoli",
        )
    )
}