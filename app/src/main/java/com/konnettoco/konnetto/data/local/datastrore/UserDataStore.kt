package com.konnettoco.konnetto.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_pref")

class UserDataStore(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_ROLE = stringPreferencesKey("user_role")
    }

    fun saveAuthToken(accessToken: String, refreshToken: String, userRole: String): Flow<Unit> = flow {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[USER_ROLE] = userRole
        }
        emit(Unit)
    }

    fun saveUserId(userId: String): Flow<Unit> = flow {
        context.dataStore.edit {
            it[USER_ID] = userId
        }
        emit(Unit)
    }

    fun getAccessToken(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN] ?: "" }

    fun getUserId(): Flow<String> = context.dataStore.data.map { it[USER_ID] ?: "" }

    fun isLoggedIn(): Flow<Boolean> = context.dataStore.data.map { !(it[ACCESS_TOKEN]).isNullOrBlank() || !(it[REFRESH_TOKEN]).isNullOrBlank() }

    suspend fun clearUserData() {
        context.dataStore.edit { it.clear() }
    }
}
