package com.areeb.weatherunion.data.api.locality_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.apiRequest
import com.areeb.weatherunion.core.network.httpClient
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse
import io.ktor.client.HttpClient

class LocalityWeatherDataApiImpl(
    private val httpClient: HttpClient = httpClient(),
) : LocalityWeatherDataApi {
    override suspend fun getWeatherData(localityId: String): ApiResponse<LocalityWeatherDataApiResponse> {
        return httpClient.apiRequest<LocalityWeatherDataApiResponse> {

        }
    }
}
