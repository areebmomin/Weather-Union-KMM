package com.areeb.weatherunion.logic.home_screen

import com.areeb.weatherunion.core.util.DEFAULT_LOCALITY_ID
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.weather_data.WeatherData

data class HomeScreenState(
    val localities: List<LocalityData> = emptyList(),
    val selectedLocalityId: String = DEFAULT_LOCALITY_ID,
    val weatherData: WeatherData? = null,
)
