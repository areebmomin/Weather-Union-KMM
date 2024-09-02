package com.areeb.weatherunion.logic.home_screen.usecases

import com.areeb.weatherunion.core.util.convertDegreeToDirection
import com.areeb.weatherunion.core.util.convertMeterPerSecToKmPerHour
import com.areeb.weatherunion.core.util.round
import com.areeb.weatherunion.data.models.WeatherUnionWeatherData
import com.areeb.weatherunion.logic.models.WeatherData
import me.tatarka.inject.annotations.Inject
import kotlin.math.roundToInt

@Inject
class WeatherDataConverterUseCase {
    operator fun invoke(weatherUnionWeatherData: WeatherUnionWeatherData): WeatherData {
        val formattedTemperature = getFormattedTemperature(weatherUnionWeatherData.temperature)
        val formattedHumidity = getFormattedHumidity(weatherUnionWeatherData.humidity)
        val formattedRainIntensity = getFormattedRainIntensity(
            weatherUnionWeatherData.rainIntensity,
        )
        val formattedRainAccumulation = getFormattedRainAccumulation(
            weatherUnionWeatherData.rainAccumulation,
        )
        val formattedWindSpeed = getFormattedWindSpeed(weatherUnionWeatherData.windSpeed)
        val formattedWindDirection = getFormattedWindDirection(
            weatherUnionWeatherData.windDirection,
        )
        val deviceDescription = getDeviceDescription(weatherUnionWeatherData.deviceType)

        return WeatherData(
            temperature = formattedTemperature,
            humidity = formattedHumidity,
            rainIntensity = formattedRainIntensity,
            rainAccumulation = formattedRainAccumulation,
            windSpeed = formattedWindSpeed,
            windDirection = formattedWindDirection,
            deviceDescription = deviceDescription,
        )
    }

    private fun getFormattedTemperature(temperature: Double?): String {
        if (temperature == null) return "NA"

        return "${temperature.roundToInt()} U+2103"
    }

    private fun getFormattedHumidity(humidity: Double?): String {
        if (humidity == null) return "NA"

        return "${humidity.roundToInt()}%"
    }

    private fun getFormattedRainIntensity(rainIntensity: Double?): String {
        if (rainIntensity == null) return "NA"

        return "${rainIntensity.round(1)} mm/min"
    }

    private fun getFormattedRainAccumulation(rainAccumulation: Double?): String {
        if (rainAccumulation == null) return "NA"

        return "${rainAccumulation.round(1)} mm"
    }

    private fun getFormattedWindSpeed(windSpeed: Double?): String {
        if (windSpeed == null) return "NA"

        val windSpeedInKmPerHour = convertMeterPerSecToKmPerHour(speedInMetersPerSec = windSpeed)
        return "$windSpeedInKmPerHour km/h"
    }

    private fun getFormattedWindDirection(windDirection: Double?): String {
        if (windDirection == null) return "NA"

        return convertDegreeToDirection(windDirection)
    }

    private fun getDeviceDescription(deviceType: Int?): String {
        return when (deviceType) {
            1 -> "Data collected using AWS (Automated Weather Station)"

            2 -> "Data collected using Rain gauge system"

            else -> "Data collected using unknown device"
        }
    }
}
