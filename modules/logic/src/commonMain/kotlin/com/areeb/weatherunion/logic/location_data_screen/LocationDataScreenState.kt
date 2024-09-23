package com.areeb.weatherunion.logic.location_data_screen

import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.utils.DEFAULT_LOCALITY
import com.areeb.weatherunion.logic.models.WeatherData

data class LocationDataScreenState(
    val isLoading: Boolean = false,
    val isLocalityDataLoading: Boolean = false,
    val selectedLocality: LocalityData = DEFAULT_LOCALITY,
    val weatherData: WeatherData = WeatherData(),
    val localityList: List<LocalityData> = emptyList(),
)
