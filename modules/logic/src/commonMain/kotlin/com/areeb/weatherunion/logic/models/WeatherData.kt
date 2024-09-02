package com.areeb.weatherunion.logic.models

data class WeatherData(
    val temperature: String = "NA",
    val humidity: String = "NA",
    val rainIntensity: String = "NA",
    val rainAccumulation: String = "NA",
    val windSpeed: String = "NA",
    val windDirection: String = "NA",
    val deviceDescription: String = "NA",
)
