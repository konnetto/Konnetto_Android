package com.konnettoco.konnetto.data.local.datastrore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class UserDataStore(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_pref")

    private val TOKEN = stringPreferencesKey("token")

    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun getToken(): String {
        return context.dataStore.data.first()[TOKEN] ?: ""
    }
}