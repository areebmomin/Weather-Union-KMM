package com.areeb.weatherunion.testing.data.api.locality_weather_data

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApi
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityLocalityWeatherData
import com.areeb.weatherunion.data.api.locality_weather_data.model.LocalityWeatherDataApiResponse
import me.tatarka.inject.annotations.Inject

@Inject
class FakeLocalityWeatherDataApiImpl : LocalityWeatherDataApi {
    var returnSuccess = true

    override suspend fun getWeatherData(localityId: String): ApiResponse<LocalityWeatherDataApiResponse> {
        return if (returnSuccess) {
            ApiResponse.Success(
                LocalityWeatherDataApiResponse(
                    status = "200",
                    message = "Success",
                    deviceType = 1,
                    localityWeatherData = LocalityLocalityWeatherData(
                        temperature = 19.5,
                        humidity = 12.4,
                        windSpeed = 1.5,
                        windDirection = 34.0,
                        rainIntensity = 12.4,
                        rainAccumulation = 5.5,
                    ),
                ),
            )
        } else {
            ApiResponse.Error.HttpError(
                code = 404,
                errorBody = null,
                errorMessage = "Something went wrong",
            )
        }
    }
}
