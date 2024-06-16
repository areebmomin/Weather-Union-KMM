package com.areeb.weatherunion.data.api.lat_lon_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.apiRequest
import com.areeb.weatherunion.core.network.httpClient
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonWeatherDataApiResponse
import io.ktor.client.HttpClient

class LatLonWeatherDataApiImpl(
    private val httpClient: HttpClient = httpClient(),
) : LatLonWeatherDataApi {
    override suspend fun getWeatherData(
        lat: Float,
        lon: Float
    ): ApiResponse<LatLonWeatherDataApiResponse> {
        return httpClient.apiRequest<LatLonWeatherDataApiResponse> {

        }
    }
}
