package com.areeb.weatherunion.testing.data.api.lat_lon_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApi
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonLocalityWeatherData
import com.areeb.weatherunion.data.api.lat_lon_weather_data.model.LatLonWeatherDataApiResponse
import me.tatarka.inject.annotations.Inject

@Inject
class FakeLatLonWeatherDataApiImpl : LatLonWeatherDataApi {
    override suspend fun getWeatherData(
        latitude: Float,
        longitude: Float,
    ): ApiResponse<LatLonWeatherDataApiResponse> {
        return ApiResponse.Success(
            LatLonWeatherDataApiResponse(
                status = "200",
                message = "Success",
                deviceType = 1,
                localityWeatherData = LatLonLocalityWeatherData(
                    temperature = 19.5,
                    humidity = 12.4,
                    windSpeed = 1.5,
                    windDirection = 34.0,
                    rainIntensity = 12.4,
                    rainAccumulation = 5.5,
                ),
            ),
        )
    }
}
