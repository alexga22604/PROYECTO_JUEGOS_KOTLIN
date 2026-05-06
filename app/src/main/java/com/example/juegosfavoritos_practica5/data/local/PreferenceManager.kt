package com.example.juegosfavoritos_practica5.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class PreferenceManager(private val context: Context) {
    private val PLATFORM_FILTER_KEY = stringPreferencesKey("platform_filter")
    private val SORT_ORDER_KEY = stringPreferencesKey("sort_order")
    val platformFilter: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[PLATFORM_FILTER_KEY]
    }
    val sortOrder: Flow<SortType> = context.dataStore.data.map { prefs ->
        val sortName = prefs[SORT_ORDER_KEY] ?: SortType.NAME.name
        SortType.valueOf(sortName)
    }

    suspend fun savePlatformFilter(platform: String?) {
        context.dataStore.edit { prefs ->
            if (platform == null) {
                prefs.remove(PLATFORM_FILTER_KEY)
            } else {
                prefs[PLATFORM_FILTER_KEY] = platform
            }
        }
    }

    suspend fun saveSortOrder(sortType: SortType) {
        context.dataStore.edit { prefs ->
            prefs[SORT_ORDER_KEY] = sortType.name
        }
    }
}