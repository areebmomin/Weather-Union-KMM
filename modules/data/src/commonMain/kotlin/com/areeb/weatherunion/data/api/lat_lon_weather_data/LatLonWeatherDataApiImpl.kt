package com.areeb.weatherunion.data.api.lat_lon_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.apiRequest
import com.areeb.weatherunion.core.network.httpClient
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonWeatherDataApiResponse
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import io.ktor.http.path

class LatLonWeatherDataApiImpl(
    private val httpClient: HttpClient = httpClient(),
) : LatLonWeatherDataApi {

    override suspend fun getWeatherData(
        lat: Float,
        lon: Float
    ): ApiResponse<LatLonWeatherDataApiResponse> {
        return httpClient.apiRequest<LatLonWeatherDataApiResponse> {
            url {
                method = HttpMethod.Get
                path("/get_weather_data")
                parameters.append("latitude", lat.toString())
                parameters.append("longitude", lon.toString())
            }
        }
    }
}
