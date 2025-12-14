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
import kotlinx.coroutines.flow.transform
import java.time.OffsetDateTime
import java.time.format.DateTimeParseException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_pref")

class UserDataStore(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

        private val REFRESH_TOKEN_EXPIRED_AT = stringPreferencesKey("refresh_token_expired_at")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_ROLE = stringPreferencesKey("user_role")
    }

    fun saveAuthToken(accessToken: String, refreshToken: String, refeshTokenExpiredAt: String, userRole: String): Flow<Unit> = flow {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[REFRESH_TOKEN_EXPIRED_AT] = refeshTokenExpiredAt
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

    fun isLoggedIn(): Flow<Boolean> = context.dataStore.data.transform { preferences ->
        val refreshToken = preferences[REFRESH_TOKEN]
        val refreshTokenExpiredAt = preferences[REFRESH_TOKEN_EXPIRED_AT]

        if (refreshToken.isNullOrBlank() || refreshTokenExpiredAt.isNullOrBlank()) {
            emit(false)
            return@transform
        }

        try {
            val expiryDateTime = OffsetDateTime.parse(refreshTokenExpiredAt)
            if (OffsetDateTime.now().isAfter(expiryDateTime)) {
                clearUserData()
                emit(false)
            } else {
                emit(!preferences[ACCESS_TOKEN].isNullOrBlank())
            }
        } catch (e: DateTimeParseException) {
            // Handle parsing error, maybe log it
            clearUserData()
            emit(false)
        }
    }

    suspend fun clearUserData() {
        context.dataStore.edit { it.clear() }
    }
}
