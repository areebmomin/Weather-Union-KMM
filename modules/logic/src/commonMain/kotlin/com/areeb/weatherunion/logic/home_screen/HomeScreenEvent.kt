package com.areeb.weatherunion.logic.home_screen

sealed interface HomeScreenEvent

data class Loading(val isLoading: Boolean) : HomeScreenEvent

data class LocalityDataLoading(val isLoading: Boolean) : HomeScreenEvent

data class Error(val message: String) : HomeScreenEvent
