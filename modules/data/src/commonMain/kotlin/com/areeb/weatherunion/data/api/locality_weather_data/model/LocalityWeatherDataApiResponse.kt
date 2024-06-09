package com.areeb.weatherunion.data.api.locality_weather_data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalityWeatherDataApiResponse(
    val status: String,
    val message: String,
    @SerialName("device_type")
    val deviceType: String,
    @SerialName("locality_weather_data")
    val localityWeatherData: LocalityLocalityWeatherData,
)

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
