package com.areeb.weatherunion.data.database.dao

import com.areeb.weatherunion.data.LocalityQueries
import com.areeb.weatherunion.data.locality_data.localityListJson
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.locality_data.model.LocalityData.Companion.toLocalityData
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Inject

interface LocalityDao {
    fun insertLocalities()
    fun getLocalitiesMap(): Map<String, List<LocalityData>>
}

@Inject
class LocalityDaoImpl(private val localityQueries: LocalityQueries) : LocalityDao {

    override fun insertLocalities() {
        fun getLocalityListFromJson(): List<LocalityData> {
            return Json.decodeFromString<List<LocalityData>>(localityListJson)
        }

        val list = getLocalityListFromJson()

        localityQueries.transaction {
            list.forEach { data ->
                localityQueries.insertLocality(
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

    override fun getLocalitiesMap(): Map<String, List<LocalityData>> {
        val localities = localityQueries.getAllLocalities()
            .executeAsList()
            .map { it.toLocalityData() }

        return localities
            .groupBy { it.cityName }
    }
}
