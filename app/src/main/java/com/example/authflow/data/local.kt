package com.example.authflow.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// 1) Attach a DataStore to Context
private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

@Singleton
class TokenStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // 2) Key for our token
    private object Keys {
        val TOKEN: Preferences.Key<String> = stringPreferencesKey("auth_token")
    }

    // 3) Observe token as a Flow<String?> (null means logged out)
    val tokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[Keys.TOKEN]
    }

    // 4) Save token
    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.TOKEN] = token
        }
    }

    // 5) Clear token (logout)
    suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(Keys.TOKEN)
        }
    }
}