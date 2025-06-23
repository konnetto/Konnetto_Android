package com.zulfadar.konnetto.data.repository

import com.zulfadar.konnetto.data.FakeFriendRequestData
import com.zulfadar.konnetto.data.model.FriendRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FriendRequestRepository {
    private val friendRequests = mutableListOf<FriendRequest>()

    init {
        if (friendRequests.isEmpty()) {
            FakeFriendRequestData.dummyFriendRequestData.forEach {
                friendRequests.add(it)
            }
        }
    }

    fun getAllFriendRequests() : Flow<List<FriendRequest>> {
        return flowOf(friendRequests)
    }

    companion object {
        @Volatile
        private var instance: FriendRequestRepository? = null

        fun getInstance(): FriendRequestRepository =
            instance ?: synchronized(this) {
                FriendRequestRepository().apply {
                    instance = this
                }
            }
    }
}