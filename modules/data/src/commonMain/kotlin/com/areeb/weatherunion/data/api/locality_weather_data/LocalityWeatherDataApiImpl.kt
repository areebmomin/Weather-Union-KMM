package com.areeb.weatherunion.data.api.locality_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.apiRequest
import com.areeb.weatherunion.core.network.http_client.HttpClientFactory
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse
import com.areeb.weatherunion.data.database.dao.ApiKeyDao
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.path
import me.tatarka.inject.annotations.Inject

@Inject
class LocalityWeatherDataApiImpl(
    private val httpClientFactory: HttpClientFactory,
    private val apiKeyDao: ApiKeyDao,
) : LocalityWeatherDataApi {

    override suspend fun getWeatherData(localityId: String): ApiResponse<LocalityWeatherDataApiResponse> {
        return httpClientFactory.client.apiRequest<LocalityWeatherDataApiResponse> {
            url {
                method = HttpMethod.Get
                path("get_locality_weather_data")
                header("x-zomato-api-key", apiKeyDao.getWeatherUnionApiKey())
                parameter("locality_id", localityId)
            }
        }
    }
}
