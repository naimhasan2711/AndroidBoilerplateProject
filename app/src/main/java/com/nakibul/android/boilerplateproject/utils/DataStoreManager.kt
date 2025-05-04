package com.nakibul.android.boilerplateproject.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    private val gson = Gson()

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "android_boilerplate_code")

        @Volatile
        private var INSTANCE: DataStoreManager? = null

        fun getInstance(context: Context): DataStoreManager {
            return INSTANCE ?: synchronized(this) {
                val instance = DataStoreManager(context.dataStore)
                INSTANCE = instance
                instance
            }
        }

        /*
        val SHOULD_LOG_OUT = booleanPreferencesKey(CommonConstant.SHOULD_LOG_OUT)
        val USER_TOKEN = stringPreferencesKey(CommonConstant.USER_TOKEN)
        val IS_EXISTING_USER_LOGIN = booleanPreferencesKey(CommonConstant.IS_EXISTING_USER_LOGIN)
        val REFRESH_TOKEN_EXP_TIME = stringPreferencesKey(CommonConstant.REFRESH_TOKEN_EXP_TIME)
        val AVERAGE_BILLING_REASON_ID = intPreferencesKey(CommonConstant.AVERAGE_BILLING_REASON_ID)
        */

    }

    // Save data
    fun put(key: Preferences.Key<String>, value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun clear(key: Preferences.Key<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences.remove(key)
            }
        }
    }

    fun put(key: Preferences.Key<Boolean>, value: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun put(key: Preferences.Key<Int>, value: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun put(key: Preferences.Key<Long>, value: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    // Retrieve data
    fun getString(key: Preferences.Key<String>): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    fun getBoolean(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: false
        }
    }

    fun getInt(key: Preferences.Key<Int>): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: 0
        }
    }

    fun getLong(key: Preferences.Key<Long>): Flow<Long> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: 0L
        }
    }

    // Clear data
    fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }

    // Save custom object
    fun <T> putObject(key: Preferences.Key<String>, value: T) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                val jsonString = gson.toJson(value)
                preferences[key] = jsonString
            }
        }
    }

    // Retrieve custom object
    fun <T> getObject(key: Preferences.Key<String>, clazz: Class<T>): Flow<T?> {
        return dataStore.data.map { preferences ->
            val jsonString = preferences[key]
            jsonString?.let { gson.fromJson(it, clazz) }
        }
    }
}