package com.areeb.weatherunion.data.api.locality_weather_data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalityWeatherDataApiRequest(
    @SerialName("locality_id")
    val localityId: String,
)
