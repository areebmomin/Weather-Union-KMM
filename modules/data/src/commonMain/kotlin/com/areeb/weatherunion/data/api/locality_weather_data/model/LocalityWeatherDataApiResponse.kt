package com.areeb.weatherunion.data.api.locality_weather_data.model

import com.areeb.weatherunion.data.weather_data.WeatherData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalityWeatherDataApiResponse(
    val status: String,
    val message: String,
    @SerialName("device_type")
    val deviceType: Int? = null,
    @SerialName("locality_weather_data")
    val localityWeatherData: LocalityLocalityWeatherData? = null,
) {
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
    val temperature: Double? = null,
    val humidity: Double? = null,
    @SerialName("wind_speed")
    val windSpeed: Double? = null,
    @SerialName("wind_direction")
    val windDirection: Double? = null,
    @SerialName("rain_intensity")
    val rainIntensity: Int? = null,
    @SerialName("rain_accumulation")
    val rainAccumulation: Double? = null,
)
