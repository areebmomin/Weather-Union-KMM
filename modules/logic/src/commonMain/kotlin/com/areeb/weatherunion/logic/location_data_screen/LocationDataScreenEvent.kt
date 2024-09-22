package com.areeb.weatherunion.logic.location_data_screen

sealed interface LocationDataScreenEvent

data class Error(val message: String) : LocationDataScreenEvent
