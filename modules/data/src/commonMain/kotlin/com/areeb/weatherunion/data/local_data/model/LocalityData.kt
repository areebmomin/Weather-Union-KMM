package com.areeb.weatherunion.data.local_data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalityData(
    @SerialName("locality_id")
    val localityId: String,
    @SerialName("city_name")
    val cityName: String,
    @SerialName("locality_name")
    val localityName: String,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("device_type")
    val deviceType: Int,
)
