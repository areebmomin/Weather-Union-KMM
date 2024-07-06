package com.areeb.weatherunion.data.preference

import kotlinx.coroutines.flow.Flow

interface WeatherUnionPreferences {
    suspend fun updateLastSelectedLocalityId(localityId: String)
    fun getLastSelectedLocalityId(): Flow<String?>
}
