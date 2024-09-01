package com.areeb.weatherunion.data.repository.weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.mapSuccess
import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApi
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApi
import com.areeb.weatherunion.data.models.WeatherUnionWeatherData
import me.tatarka.inject.annotations.Inject

@Inject
class WeatherDataRepositoryImpl(
    private val latLonWeatherDataApi: LatLonWeatherDataApi,
    private val localityWeatherDataApi: LocalityWeatherDataApi,
) : WeatherDataRepository {
    override suspend fun getWeatherData(
        lat: Float,
        lon: Float
    ): ApiResponse<WeatherUnionWeatherData> {
        return latLonWeatherDataApi.getWeatherData(lat = lat, lon = lon).mapSuccess {
            it.toWeatherData()
        }
    }

    override suspend fun getWeatherData(locationId: String): ApiResponse<WeatherUnionWeatherData> {
        return localityWeatherDataApi.getWeatherData(localityId = locationId).mapSuccess {
            it.toWeatherData()
        }
    }
}
