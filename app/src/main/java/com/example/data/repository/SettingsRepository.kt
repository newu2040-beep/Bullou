package com.example.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {
    private val COMPACT_MODE_KEY = booleanPreferencesKey("compact_mode")
    private val THEME_KEY = stringPreferencesKey("app_theme")
    private val CURRENCY_KEY = stringPreferencesKey("currency")

    val compactModeFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[COMPACT_MODE_KEY] ?: false
    }

    val themeFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[THEME_KEY] ?: "Original"
    }
    
    val currencyFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[CURRENCY_KEY] ?: "USD"
    }

    suspend fun setCompactMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[COMPACT_MODE_KEY] = enabled
        }
    }

    suspend fun setTheme(themeName: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = themeName
        }
    }
    
    suspend fun setCurrency(currency: String) {
        context.dataStore.edit { preferences ->
            preferences[CURRENCY_KEY] = currency
        }
    }
}
