package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.local.datastore.UserDataStore
import com.konnettoco.konnetto.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : UserRepository {

    override fun isLoggedIn(): Flow<Boolean> {
        return userDataStore.isLoggedIn()
    }

    override fun getAccessToken(): Flow<String> {
        return userDataStore.getAccessToken()
    }

    override suspend fun saveAuthToken(accessToken: String, refreshToken: String, userRole: String): Flow<Unit> {
        return userDataStore.saveAuthToken(accessToken, refreshToken, userRole)
    }

    override suspend fun saveUserId(userId: String): Flow<Unit> {
        return userDataStore.saveUserId(userId)
    }

    override suspend fun clearUserData() {
        userDataStore.clearUserData()
    }
}
