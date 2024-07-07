package com.areeb.weatherunion.data.repository

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApi
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonWeatherDataApiResponse
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApi
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse
import me.tatarka.inject.annotations.Inject

@Inject
class WeatherDataRepositoryImpl(
    private val latLonWeatherDataApi: LatLonWeatherDataApi,
    private val localityWeatherDataApi: LocalityWeatherDataApi,
) : WeatherDataRepository {
    override suspend fun getWeatherData(
        lat: Float,
        lon: Float
    ): ApiResponse<LatLonWeatherDataApiResponse> {
        return latLonWeatherDataApi.getWeatherData(lat = lat, lon = lon)
    }

    override suspend fun getWeatherData(locationId: String): ApiResponse<LocalityWeatherDataApiResponse> {
        return localityWeatherDataApi.getWeatherData(localityId = locationId)
    }
}
