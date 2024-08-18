package com.areeb.weatherunion.data.api.locality_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.apiRequest
import com.areeb.weatherunion.core.network.http_client.HttpClientFactory
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.path
import me.tatarka.inject.annotations.Inject

@Inject
class LocalityWeatherDataApiImpl(private val httpClientFactory: HttpClientFactory) :
    LocalityWeatherDataApi {

    override suspend fun getWeatherData(localityId: String): ApiResponse<LocalityWeatherDataApiResponse> {
        return httpClientFactory.client.apiRequest<LocalityWeatherDataApiResponse> {
            url {
                method = HttpMethod.Get
                path("get_locality_weather_data")
                parameter("locality_id", localityId)
            }
        }
    }
}
