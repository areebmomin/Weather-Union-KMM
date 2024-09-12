package com.areeb.weatherunion.data.preference

import com.areeb.weatherunion.data.locality_data.model.LocalityData
import kotlinx.coroutines.flow.Flow

interface WeatherUnionPreferences {
    suspend fun updateLastSelectedLocality(locality: LocalityData)
    fun getLastSelectedLocality(): Flow<LocalityData?>
}
