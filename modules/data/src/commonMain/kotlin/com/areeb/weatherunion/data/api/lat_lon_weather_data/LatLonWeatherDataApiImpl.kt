package com.areeb.weatherunion.data.api.lat_lon_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.apiRequest
import com.areeb.weatherunion.core.network.http_client.HttpClientFactory
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonWeatherDataApiResponse
import com.areeb.weatherunion.data.database.dao.ApiKeyDao
import io.ktor.client.request.header
import io.ktor.http.HttpMethod
import io.ktor.http.path
import me.tatarka.inject.annotations.Inject

@Inject
class LatLonWeatherDataApiImpl(
    private val httpClientFactory: HttpClientFactory,
    private val apiKeyDao: ApiKeyDao,
) : LatLonWeatherDataApi {

    override suspend fun getWeatherData(
        latitude: Float,
        longitude: Float
    ): ApiResponse<LatLonWeatherDataApiResponse> {
        return httpClientFactory.client.apiRequest<LatLonWeatherDataApiResponse> {
            url {
                method = HttpMethod.Get
                path("get_weather_data")
                header("x-zomato-api-key", apiKeyDao.getWeatherUnionApiKey())
                parameters.append("latitude", latitude.toString())
                parameters.append("longitude", longitude.toString())
            }
        }
    }
}
