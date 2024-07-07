package com.areeb.weatherunion.data.locality_data.model

import com.areeb.weatherunion.data.Locality
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
) {
    companion object {
        fun Locality.toLocalityData(): LocalityData =
            LocalityData(
                localityId = locality_id,
                cityName = city_name,
                localityName = locality_name,
                latitude = latitude,
                longitude = longitude,
                deviceType = device_type.toInt(),
            )
    }
}
