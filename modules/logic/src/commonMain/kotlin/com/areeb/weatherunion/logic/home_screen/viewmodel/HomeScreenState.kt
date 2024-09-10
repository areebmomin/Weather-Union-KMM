package com.areeb.weatherunion.logic.home_screen.viewmodel

import com.areeb.weatherunion.core.util.DEFAULT_LOCALITY_ID
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.logic.models.WeatherData

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isLocalityDataLoading: Boolean = false,
    val selectedLocalityId: String = DEFAULT_LOCALITY_ID,
    val weatherData: WeatherData = WeatherData(),
    val localitiesMap: Map<LocalityData, List<LocalityData>> = emptyMap(),
)
