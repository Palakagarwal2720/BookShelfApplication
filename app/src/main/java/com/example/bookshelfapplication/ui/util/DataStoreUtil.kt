package com.example.bookshelfapplication.ui.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreUtil(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userEmail")
    }

    object Keys {
        const val USER_LOGIN = "USER_LOGIN"
        const val USER_REGISTER = "USER_REGISTERED"
    }

    /**
     *To save string in datastore
     * */
    suspend fun saveString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    /**
     * To return string stored with that key in datastore
     * */
    suspend fun getString(
        key: String,
        defaultValue: String = ""
    ): String {
        val flow = context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }

        return flow.first()?:defaultValue
    }

    /**
     *To save boolean data in datastore
     * */
    suspend fun saveBoolean(key: String, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    /**
     *To get boolean data in datastore
     * */
    suspend fun getBoolean(
        key: String,
        defaultValue: Boolean = false
    ): Boolean {
        val flow = context.dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)]
        }
        return flow.first() ?: defaultValue
    }

    /**
     *To save long data in datastore
     * */
    suspend fun saveLong(key: String, value: Long) {
        context.dataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = value
        }
    }

    /**
     *To extract long data with that key from datastore
     * */
    suspend fun getLong(
        key: String,
        defaultValue: Long = 0L
    ): Long {
        val flow = context.dataStore.data.map { preferences ->
            preferences[longPreferencesKey(key)]
        }
        return flow.first() ?: defaultValue
    }

    /**
     *To save int data in datastore
     * */
    suspend fun saveInt(key: String, value: Int) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    /**
     *To extract int data with that key from datastore
     * */
    suspend fun getInt(
        key: String,
        defaultValue: Int = 0
    ): Int {
        val flow = context.dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)]
        }
        return flow.first() ?: defaultValue
    }

    /**
     *To clear datastore
     * */
    suspend fun clearDataStore() {
        context.dataStore.edit {
            it.clear()
        }

    }

}