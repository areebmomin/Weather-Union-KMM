package com.areeb.weatherunion.data.weather_data

data class WeatherData(
    val deviceType: Int?,
    val temperature: Double?,
    val humidity: Double?,
    val windSpeed: Double?,
    val windDirection: Double?,
    val rainIntensity: Double?,
    val rainAccumulation: Double?,
)
