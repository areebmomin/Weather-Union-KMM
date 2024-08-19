package com.areeb.weatherunion.data.repository.weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.weather_data.WeatherData

interface WeatherDataRepository {
    suspend fun getWeatherData(lat: Float, lon: Float): ApiResponse<WeatherData>
    suspend fun getWeatherData(locationId: String): ApiResponse<WeatherData>
}
