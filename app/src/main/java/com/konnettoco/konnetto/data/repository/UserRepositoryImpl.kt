package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.local.datastrore.UserDataStore
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

    override suspend fun saveToken(accessToken: String, refreshToken: String, userId: String, userRole: String) {
        userDataStore.saveToken(accessToken, refreshToken, userId, userRole)
    }

    override suspend fun clearUserData() {
        userDataStore.clearUserData()
    }
}
