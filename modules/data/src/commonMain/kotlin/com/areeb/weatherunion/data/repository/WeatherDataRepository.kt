package com.areeb.weatherunion.data.repository

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonWeatherDataApiResponse
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse

interface WeatherDataRepository {
    suspend fun getWeatherData(lat: Float, lon: Float): ApiResponse<LatLonWeatherDataApiResponse>
    suspend fun getWeatherData(locationId: String): ApiResponse<LocalityWeatherDataApiResponse>
}
