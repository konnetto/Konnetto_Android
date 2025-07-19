package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.FakeUserDataSource
import com.konnettoco.konnetto.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserRepository {
    private val currentUser = mutableListOf<User>()

    init {
        if (currentUser.isEmpty()) {
            FakeUserDataSource.currentUserDummy
        }
    }

    fun getCurrentUser(): Flow<List<User>> {
        return flowOf(currentUser)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository =
            instance ?: synchronized(this) {
                UserRepository().apply {
                    instance = this
                }
            }
    }
}