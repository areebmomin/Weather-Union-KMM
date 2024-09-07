package com.areeb.weatherunion.logic.models

data class WeatherData(
    val temperature: WeatherDataTemperature = WeatherDataTemperature(),
    val humidity: WeatherDataHumidity = WeatherDataHumidity(),
    val windSpeed: WeatherDataWindSpeed = WeatherDataWindSpeed(),
    val windDirection: WeatherDataWindDirection = WeatherDataWindDirection(),
    val rainIntensity: WeatherDataRainIntensity = WeatherDataRainIntensity(),
    val rainAccumulation: WeatherDataRainAccumulation = WeatherDataRainAccumulation(),
    val deviceDescription: String = "NA",
)

data class WeatherDataTemperature(
    val temperature: String = "",
    val unit: String = "NA",
)

data class WeatherDataHumidity(
    val humidity: String = "",
    val unit: String = "NA",
)

data class WeatherDataWindSpeed(
    val windSpeed: String = "",
    val unit: String = "NA",
)

data class WeatherDataWindDirection(
    val windDirection: String = "",
    val error: String = "NA",
)

data class WeatherDataRainIntensity(
    val rainIntensity: String = "",
    val unit: String = "NA",
)

data class WeatherDataRainAccumulation(
    val rainAccumulation: String = "",
    val unit: String = "NA",
)
