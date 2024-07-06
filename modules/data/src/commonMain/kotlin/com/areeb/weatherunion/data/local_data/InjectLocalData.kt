package com.areeb.weatherunion.data.local_data

import com.areeb.weatherunion.data.WeatherUnionDatabase
import com.areeb.weatherunion.data.local_data.model.LocalityData
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Inject

@Inject
class InjectLocalData(private val database: WeatherUnionDatabase) {

    private fun getLocalityListFromJson(): List<LocalityData> {
        return Json.decodeFromString<List<LocalityData>>(localityListJson)
    }

    fun insertLocalities() {
        val list = getLocalityListFromJson()

        database.localityQueries.transaction {
            list.forEach { data ->
                database.localityQueries.insertLocality(
                    locality_id = data.localityId,
                    city_name = data.cityName,
                    locality_name = data.localityName,
                    latitude = data.latitude,
                    longitude = data.longitude,
                    device_type = data.deviceType.toLong(),
                )
            }
        }
    }
}
