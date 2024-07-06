package com.areeb.weatherunion.data.preference

import androidx.datastore.core.DataStore
import com.areeb.weatherunion.data.PreferenceData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherUnionPreferenceImpl(private val dataStore: DataStore<PreferenceData> = getDataStore()) :
    WeatherUnionPreferences {
    override suspend fun updateLastSelectedLocalityId(localityId: String) {
        dataStore.updateData { data ->
            data.copy(lastSelectedLocalityId = localityId)
        }
    }

    override fun getLastSelectedLocalityId(): Flow<String?> {
        return dataStore.data.map { data ->
            data.lastSelectedLocalityId
        }
    }
}
