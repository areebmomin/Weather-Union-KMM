package com.areeb.weatherunion.data.api.lat_lon_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonWeatherDataApiResponse

interface LatLonWeatherDataApi {
    suspend fun getWeatherData(lat: Float, lon: Float): ApiResponse<LatLonWeatherDataApiResponse>
}
