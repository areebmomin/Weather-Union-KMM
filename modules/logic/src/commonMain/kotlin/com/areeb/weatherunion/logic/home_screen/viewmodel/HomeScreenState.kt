package com.areeb.weatherunion.logic.home_screen.viewmodel

import com.areeb.weatherunion.core.util.DEFAULT_LOCALITY_ID
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.models.WeatherUnionWeatherData

data class HomeScreenState(
    val selectedLocalityId: String = DEFAULT_LOCALITY_ID,
    val weatherUnionWeatherData: WeatherUnionWeatherData? = null,
    val localities: List<LocalityData> = emptyList(),
)
