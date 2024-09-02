package com.areeb.weatherunion.logic.home_screen.viewmodel

sealed interface HomeScreenEvent

data class Error(val message: String) : HomeScreenEvent
