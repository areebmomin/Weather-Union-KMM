package com.areeb.weatherunion.data.preference

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@Inject
class WeatherUnionPreferenceImpl(private val dataStoreFactory: ProtoDataStoreFactory) :
    WeatherUnionPreferences {
    override suspend fun updateLastSelectedLocalityId(localityId: String) {
        dataStoreFactory.dataStore.updateData { data ->
            data.copy(lastSelectedLocalityId = localityId)
        }
    }

    override fun getLastSelectedLocalityId(): Flow<String?> {
        return dataStoreFactory.dataStore.data.map { data ->
            data.lastSelectedLocalityId
        }
    }
}
