package com.areeb.weatherunion.logic.home_screen.usecases

import com.areeb.weatherunion.core.util.convertDegreeToDirection
import com.areeb.weatherunion.core.util.convertMeterPerSecToKmPerHour
import com.areeb.weatherunion.core.util.round
import com.areeb.weatherunion.data.models.WeatherUnionWeatherData
import com.areeb.weatherunion.logic.models.WeatherData
import com.areeb.weatherunion.logic.models.WeatherDataHumidity
import com.areeb.weatherunion.logic.models.WeatherDataRainAccumulation
import com.areeb.weatherunion.logic.models.WeatherDataRainIntensity
import com.areeb.weatherunion.logic.models.WeatherDataTemperature
import com.areeb.weatherunion.logic.models.WeatherDataWindDirection
import com.areeb.weatherunion.logic.models.WeatherDataWindSpeed
import me.tatarka.inject.annotations.Inject
import kotlin.math.roundToInt

@Inject
class WeatherDataConverterUseCase {
    operator fun invoke(weatherUnionWeatherData: WeatherUnionWeatherData): WeatherData {
        val formattedTemperature = getFormattedTemperature(weatherUnionWeatherData.temperature)
        val formattedHumidity = getFormattedHumidity(weatherUnionWeatherData.humidity)
        val formattedWindSpeed = getFormattedWindSpeed(weatherUnionWeatherData.windSpeed)
        val formattedWindDirection = getFormattedWindDirection(
            weatherUnionWeatherData.windDirection,
        )
        val formattedRainIntensity = getFormattedRainIntensity(
            weatherUnionWeatherData.rainIntensity,
        )
        val formattedRainAccumulation = getFormattedRainAccumulation(
            weatherUnionWeatherData.rainAccumulation,
        )
        val deviceDescription = getDeviceDescription(weatherUnionWeatherData.deviceType)

        return WeatherData(
            temperature = formattedTemperature,
            humidity = formattedHumidity,
            windSpeed = formattedWindSpeed,
            windDirection = formattedWindDirection,
            rainIntensity = formattedRainIntensity,
            rainAccumulation = formattedRainAccumulation,
            deviceDescription = deviceDescription,
        )
    }

    private fun getFormattedTemperature(temperature: Double?): WeatherDataTemperature {
        if (temperature == null) return WeatherDataTemperature()

        return WeatherDataTemperature(
            temperature = "${temperature.roundToInt()}",
            unit = "\u2103",
        )
    }

    private fun getFormattedHumidity(humidity: Double?): WeatherDataHumidity {
        if (humidity == null) return WeatherDataHumidity()

        return WeatherDataHumidity(
            humidity = "${humidity.roundToInt()}",
            unit = "%",
        )
    }

    private fun getFormattedRainIntensity(rainIntensity: Double?): WeatherDataRainIntensity {
        if (rainIntensity == null) return WeatherDataRainIntensity()

        return WeatherDataRainIntensity(
            rainIntensity = "${rainIntensity.round(1)}",
            unit = " mm/min"
        )
    }

    private fun getFormattedRainAccumulation(rainAccumulation: Double?): WeatherDataRainAccumulation {
        if (rainAccumulation == null) return WeatherDataRainAccumulation()

        return WeatherDataRainAccumulation(
            rainAccumulation = "${rainAccumulation.round(1)}",
            unit = " mm"
        )
    }

    private fun getFormattedWindSpeed(windSpeed: Double?): WeatherDataWindSpeed {
        if (windSpeed == null) return WeatherDataWindSpeed()

        val windSpeedInKmPerHour = convertMeterPerSecToKmPerHour(speedInMetersPerSec = windSpeed)
        return WeatherDataWindSpeed(
            windSpeed = windSpeedInKmPerHour.round(1).toString(),
            unit = " km/h"
        )
    }

    private fun getFormattedWindDirection(windDirection: Double?): WeatherDataWindDirection {
        if (windDirection == null) return WeatherDataWindDirection()

        return WeatherDataWindDirection(
            windDirection = convertDegreeToDirection(windDirection),
            windDirectionDegree = windDirection.toFloat(),
            error = "",
        )
    }

    private fun getDeviceDescription(deviceType: Int?): String {
        return when (deviceType) {
            1 -> "Data collected using AWS (Automated Weather Station)"

            2 -> "Data collected using Rain gauge system"

            else -> "NA"
        }
    }
}
