package com.areeb.weatherunion.logic.home_screen.viewmodel

sealed interface HomeScreenAction

data class OnCitySelected(val localityId: String) : HomeScreenAction

data class OnLocalitySelected(val localityId: String) : HomeScreenAction
