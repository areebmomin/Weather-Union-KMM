package com.areeb.weatherunion.data.repository.weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.models.WeatherUnionWeatherData

interface WeatherDataRepository {
    suspend fun getWeatherData(lat: Float, lon: Float): ApiResponse<WeatherUnionWeatherData>
    suspend fun getWeatherData(locationId: String): ApiResponse<WeatherUnionWeatherData>
}
