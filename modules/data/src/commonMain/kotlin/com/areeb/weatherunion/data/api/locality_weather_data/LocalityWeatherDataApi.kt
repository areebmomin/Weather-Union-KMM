package com.areeb.weatherunion.data.api.locality_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse

interface LocalityWeatherDataApi {
    suspend fun getWeatherData(localityId: String): ApiResponse<LocalityWeatherDataApiResponse>
}
