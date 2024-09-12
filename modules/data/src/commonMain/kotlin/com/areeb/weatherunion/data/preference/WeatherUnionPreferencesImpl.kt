package com.areeb.weatherunion.data.preference

import com.areeb.weatherunion.data.locality_data.model.LocalityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@Inject
class WeatherUnionPreferenceImpl(private val dataStoreFactory: ProtoDataStoreFactory) :
    WeatherUnionPreferences {
    override suspend fun updateLastSelectedLocality(locality: LocalityData) {
        dataStoreFactory.dataStore.updateData { data ->
            data.copy(
                localityId = locality.localityId,
                cityName = locality.cityName,
                localityName = locality.localityName,
                latitude = locality.latitude,
                longitude = locality.longitude,
                deviceType = locality.deviceType,
            )
        }
    }

    override fun getLastSelectedLocality(): Flow<LocalityData?> {
        return dataStoreFactory.dataStore.data.map { data ->
            LocalityData(
                localityId = data.localityId,
                cityName = data.cityName,
                localityName = data.localityName,
                latitude = data.latitude,
                longitude = data.longitude,
                deviceType = data.deviceType,
            )
        }
    }
}
