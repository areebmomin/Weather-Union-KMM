package com.areeb.weatherunion.logic.location_data_screen

import com.areeb.weatherunion.data.locality_data.model.LocalityData

sealed interface LocationDataScreenAction

data class OnLocalitySelected(val locality: LocalityData) : LocationDataScreenAction
