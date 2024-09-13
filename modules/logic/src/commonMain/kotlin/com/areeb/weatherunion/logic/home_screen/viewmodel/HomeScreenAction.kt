package com.areeb.weatherunion.logic.home_screen.viewmodel

import com.areeb.weatherunion.data.locality_data.model.LocalityData

sealed interface HomeScreenAction

data class OnLocalitySelected(val locality: LocalityData) : HomeScreenAction

data object RefreshWeatherData : HomeScreenAction
