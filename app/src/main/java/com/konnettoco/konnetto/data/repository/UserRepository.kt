package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.FakeUserDataSource
import com.konnettoco.konnetto.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserRepository {
    private val users = listOf(
        FakeUserDataSource.currentUserDummy,
        FakeUserDataSource.otherUserDummy1,
        FakeUserDataSource.otherUserDummy2,
        FakeUserDataSource.otherUserDummy3
    )

    fun getUserById(id: Long): User {
        return users.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("User with id $id not found")
    }

    fun getAllUsers(): Flow<List<User>> {
        return flowOf(users)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository =
            instance ?: synchronized(this) {
                UserRepository().also { instance = it }
            }
    }

//    private val currentUser = mutableListOf<User>()
//
//    init {
//        if (currentUser.isEmpty()) {
//            FakeUserDataSource.currentUserDummy
//        }
//    }
//
//    fun getCurrentUser(): Flow<List<User>> {
//        return flowOf(currentUser)
//    }
//
//    companion object {
//        @Volatile
//        private var instance: UserRepository? = null
//
//        fun getInstance(): UserRepository =
//            instance ?: synchronized(this) {
//                UserRepository().apply {
//                    instance = this
//                }
//            }
//    }
}