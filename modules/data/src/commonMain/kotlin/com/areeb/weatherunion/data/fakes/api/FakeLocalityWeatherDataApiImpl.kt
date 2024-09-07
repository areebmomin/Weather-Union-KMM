package com.areeb.weatherunion.data.fakes.api

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApi
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityLocalityWeatherData
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse
import me.tatarka.inject.annotations.Inject

@Inject
class FakeLocalityWeatherDataApiImpl : LocalityWeatherDataApi {
    override suspend fun getWeatherData(localityId: String): ApiResponse<LocalityWeatherDataApiResponse> {
        return ApiResponse.Success(
            LocalityWeatherDataApiResponse(
                status = "200",
                message = "Success",
                deviceType = 1,
                localityWeatherData = LocalityLocalityWeatherData(
                    temperature = 21.3,
                    humidity = 12.4,
                    windSpeed = 1.5,
                    windDirection = 212.9,
                    rainIntensity = 12.4,
                    rainAccumulation = 5.5,
                ),
            ),
        )
    }
}
