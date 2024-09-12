package com.areeb.weatherunion.logic.home_screen.viewmodel

import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.utils.DEFAULT_LOCALITY
import com.areeb.weatherunion.logic.models.WeatherData

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isLocalityDataLoading: Boolean = false,
    val selectedLocality: LocalityData = DEFAULT_LOCALITY,
    val weatherData: WeatherData = WeatherData(),
    val localitiesMap: Map<String, List<LocalityData>> = emptyMap(),
    val uniqueCitiesFirstLocalityList: List<LocalityData> = emptyList(),
)
