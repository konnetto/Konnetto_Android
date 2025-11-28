package com.konnettoco.konnetto.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun isLoggedIn(): Flow<Boolean>

    fun getAccessToken(): Flow<String>

    suspend fun saveAuthToken(accessToken: String, refreshToken: String, userRole: String): Flow<Unit>

    suspend fun saveUserId(userId: String): Flow<Unit>

    suspend fun clearUserData()
}