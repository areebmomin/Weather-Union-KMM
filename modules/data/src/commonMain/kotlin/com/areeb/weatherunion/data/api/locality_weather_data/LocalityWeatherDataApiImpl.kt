package com.areeb.weatherunion.data.api.locality_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.apiRequest
import com.areeb.weatherunion.core.network.httpClient
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.path

class LocalityWeatherDataApiImpl(
    private val httpClient: HttpClient = httpClient(),
) : LocalityWeatherDataApi {
    override suspend fun getWeatherData(localityId: String): ApiResponse<LocalityWeatherDataApiResponse> {
        return httpClient.apiRequest<LocalityWeatherDataApiResponse> {
            url {
                method = HttpMethod.Get
                path("/get_locality_weather_data")
                parameter("locality_id", localityId)
            }
        }
    }
}
