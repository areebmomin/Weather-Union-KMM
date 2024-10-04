package com.areeb.weatherunion.testing.logic.home_screen.usecases

import com.areeb.weatherunion.data.models.WeatherUnionWeatherData
import com.areeb.weatherunion.logic.home_screen.usecases.WeatherDataConverterUseCase
import com.areeb.weatherunion.logic.models.WeatherData
import com.areeb.weatherunion.logic.models.WeatherDataHumidity
import com.areeb.weatherunion.logic.models.WeatherDataRainAccumulation
import com.areeb.weatherunion.logic.models.WeatherDataRainIntensity
import com.areeb.weatherunion.logic.models.WeatherDataTemperature
import com.areeb.weatherunion.logic.models.WeatherDataWindDirection
import com.areeb.weatherunion.logic.models.WeatherDataWindSpeed
import kotlin.test.Test
import kotlin.test.assertEquals

class WeatherDataConverterUseCaseTest {
    @Test
    fun testNullWeatherData() {
        val weatherUnionData = WeatherUnionWeatherData(
            deviceType = null,
            temperature = null,
            humidity = null,
            windSpeed = null,
            windDirection = null,
            rainIntensity = null,
            rainAccumulation = null,
        )

        val weatherData = WeatherDataConverterUseCase().invoke(weatherUnionData)

        assertEquals(WeatherData(), weatherData)
    }

    @Test
    fun testCompleteWeatherData() {
        val weatherUnionData = WeatherUnionWeatherData(
            deviceType = 1,
            temperature = 26.4,
            humidity = 46.0,
            windSpeed = 244.8,
            windDirection = 114.7,
            rainIntensity = 32.2,
            rainAccumulation = 110.9,
        )

        val weatherData = WeatherDataConverterUseCase().invoke(weatherUnionData)

        assertEquals(
            WeatherData(
                temperature = WeatherDataTemperature(temperature = "26", unit = "\u2103"),
                humidity = WeatherDataHumidity(humidity = "46", unit = "%"),
                windSpeed = WeatherDataWindSpeed(windSpeed = "881.3", unit = " km/h"),
                windDirection = WeatherDataWindDirection(windDirection = "SE", windDirectionDegree = 114.7f, error = ""),
                rainIntensity = WeatherDataRainIntensity(rainIntensity = "32.2", unit = " mm/min"),
                rainAccumulation = WeatherDataRainAccumulation(rainAccumulation = "110.9", unit = " mm"),
                deviceDescription = "Data collected using AWS (Automated Weather Station)",
            ),
            weatherData,
        )
    }

    @Test
    fun testPartialWeatherData() {
        val weatherUnionData = WeatherUnionWeatherData(
            deviceType = 2,
            temperature = null,
            humidity = null,
            windSpeed = null,
            windDirection = 310.3,
            rainIntensity = 14.0,
            rainAccumulation = 24.5,
        )

        val weatherData = WeatherDataConverterUseCase().invoke(weatherUnionData)

        assertEquals(
            WeatherData(
                windDirection = WeatherDataWindDirection(windDirection = "NW", windDirectionDegree = 310.3f, error = ""),
                rainIntensity = WeatherDataRainIntensity(rainIntensity = "14.0", unit = " mm/min"),
                rainAccumulation = WeatherDataRainAccumulation(rainAccumulation = "24.5", unit = " mm"),
                deviceDescription = "Data collected using Rain gauge system",
            ),
            weatherData,
        )
    }
}
