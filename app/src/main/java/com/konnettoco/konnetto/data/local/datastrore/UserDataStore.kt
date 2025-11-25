package com.konnettoco.konnetto.data.local.datastrore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_pref")

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_ROLE = stringPreferencesKey("user_role")
    }

    suspend fun saveToken(accessToken: String, refreshToken: String, userId: String, userRole: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[USER_ID] = userId
            it[USER_ROLE] = userRole
        }
    }

    fun getAccessToken(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN] ?: "" }

    fun isLoggedIn(): Flow<Boolean> = context.dataStore.data.map { !(it[ACCESS_TOKEN]).isNullOrBlank() }

    suspend fun clearUserData() {
        context.dataStore.edit { it.clear() }
    }
}