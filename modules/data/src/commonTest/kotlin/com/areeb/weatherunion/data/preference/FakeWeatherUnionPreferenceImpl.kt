package com.areeb.weatherunion.data.preference

import com.areeb.weatherunion.data.locality_data.model.LocalityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.tatarka.inject.annotations.Inject

@Inject
class FakeWeatherUnionPreferenceImpl : WeatherUnionPreferences {
    private var lastSelectedLocality: LocalityData? = null

    override suspend fun updateLastSelectedLocality(locality: LocalityData) {
        lastSelectedLocality = locality
    }

    override fun getLastSelectedLocality(): Flow<LocalityData?> {
        return flow {
            emit(lastSelectedLocality)
        }
    }
}
