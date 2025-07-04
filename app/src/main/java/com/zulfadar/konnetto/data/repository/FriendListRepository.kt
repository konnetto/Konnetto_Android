package com.zulfadar.konnetto.data.repository

import com.zulfadar.konnetto.data.FakeFriendListData
import com.zulfadar.konnetto.data.model.FriendList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FriendListRepository {
    private val friendList = mutableListOf<FriendList>()

    init {
        if (friendList.isEmpty()) {
            FakeFriendListData.dummyFriendListData.forEach {
                friendList.add(it)
            }
        }
    }

    fun getAllFriendList(): Flow<List<FriendList>> {
        return flowOf(friendList)
    }

    companion object {
        @Volatile
        private var instance: FriendListRepository? = null

        fun getInstance(): FriendListRepository =
            instance ?: synchronized(this) {
                FriendListRepository().apply {
                    instance = this
                }
            }
    }
}