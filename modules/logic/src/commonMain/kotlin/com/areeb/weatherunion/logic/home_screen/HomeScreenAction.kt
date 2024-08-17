package com.areeb.weatherunion.logic.home_screen

sealed interface HomeScreenAction

data object OnCitySelected : HomeScreenAction

data object OnLocalitySelected : HomeScreenAction
