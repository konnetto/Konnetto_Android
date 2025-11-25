package com.konnettoco.konnetto.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun isLoggedIn(): Flow<Boolean>

    fun getAccessToken(): Flow<String>

    suspend fun saveToken(accessToken: String, refreshToken: String, userId: String, userRole: String)

    suspend fun clearUserData()
}