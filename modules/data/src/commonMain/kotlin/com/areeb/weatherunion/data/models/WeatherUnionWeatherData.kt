package com.areeb.weatherunion.data.models

data class WeatherUnionWeatherData(
    val deviceType: Int?,
    val temperature: Double?,
    val humidity: Double?,
    val windSpeed: Double?,
    val windDirection: Double?,
    val rainIntensity: Int?,
    val rainAccumulation: Double?,
)
