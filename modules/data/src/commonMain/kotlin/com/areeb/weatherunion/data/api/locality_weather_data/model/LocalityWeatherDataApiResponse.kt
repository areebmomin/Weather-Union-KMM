package com.areeb.weatherunion.data.api.locality_weather_data.model

import com.areeb.weatherunion.core.network.BaseResponse
import com.areeb.weatherunion.data.weather_data.WeatherData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalityWeatherDataApiResponse(
    override val status: String,
    override val message: String,
    @SerialName("device_type")
    val deviceType: Int? = null,
    @SerialName("locality_weather_data")
    val localityWeatherData: LocalityLocalityWeatherData? = null,
) : BaseResponse {
    fun toWeatherData(): WeatherData {
        return WeatherData(
            deviceType = deviceType,
            temperature = localityWeatherData?.temperature,
            humidity = localityWeatherData?.humidity,
            windSpeed = localityWeatherData?.windSpeed,
            windDirection = localityWeatherData?.windDirection,
            rainIntensity = localityWeatherData?.rainIntensity,
            rainAccumulation = localityWeatherData?.rainAccumulation,
        )
    }
}

@Serializable
data class LocalityLocalityWeatherData(
    val temperature: Double,
    val humidity: Double,
    @SerialName("wind_speed")
    val windSpeed: Double,
    @SerialName("wind_direction")
    val windDirection: Double,
    @SerialName("rain_intensity")
    val rainIntensity: Double,
    @SerialName("rain_accumulation")
    val rainAccumulation: Double,
)
