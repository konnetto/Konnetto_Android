package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.data.model.FriendList

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
            profilePict = R.drawable.logo,
            username = "comoli",
        )
    )
}