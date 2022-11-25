package io.oworld.ohchild

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import io.oworld.ohchild.UserPreferencesRepo.PreferencesKeys.DIGIT
import io.oworld.ohchild.UserPreferencesRepo.PreferencesKeys.MINUTE
import io.oworld.ohchild.UserPreferencesRepo.PreferencesKeys.SECOND
import io.oworld.ohchild.UserPreferencesRepo.PreferencesKeys.STAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class UserPreferences(val stage: Int, val digit: Int, val minute: Long, val second: Long)

class UserPreferencesRepo(private val dataStore: DataStore<Preferences>) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private object PreferencesKeys {
        val STAGE = intPreferencesKey("stage")
        val DIGIT = intPreferencesKey("digit")
        val MINUTE = longPreferencesKey("minute")
        val SECOND = longPreferencesKey("second")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val stage = preferences[STAGE] ?: 0
        val digit = preferences[DIGIT] ?: 1
        val minute = preferences[MINUTE] ?: 3L
        val second = preferences[SECOND] ?: 0L
        UserPreferences(stage, digit, minute, second)
    }

    fun updateStage(stage: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStore.edit { preferences -> preferences[STAGE] = stage }
        }
    }

    fun updateDigit(digit: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStore.edit { preferences -> preferences[DIGIT] = digit }
        }
    }

    fun updateMinute(minute: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStore.edit { preferences -> preferences[MINUTE] = minute }
        }
    }

    fun updateSecond(second: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStore.edit { preferences -> preferences[SECOND] = second }
        }
    }
}



