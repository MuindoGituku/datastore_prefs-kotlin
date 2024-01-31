package com.example.assignment1.dataman

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRecordsManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserEmail")

        val usernameKey = stringPreferencesKey("username")
        val emailAddressKey = stringPreferencesKey("email_address")
        val idKey = stringPreferencesKey("id")
    }

    val getUsername: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[usernameKey] ?: ""
        }
    val getEmailAddress: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[emailAddressKey] ?: ""
        }
    val getID: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[idKey] ?: ""
        }

    suspend fun saveUserData(username: String, emailAddress: String, id:String) {
        context.dataStore.edit { preferences ->
            preferences[usernameKey] = username
            preferences[emailAddressKey] = emailAddress
            preferences[idKey] = id
        }
    }
}