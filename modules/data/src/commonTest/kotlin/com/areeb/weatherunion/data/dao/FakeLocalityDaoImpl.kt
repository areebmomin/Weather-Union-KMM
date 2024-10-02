package com.areeb.weatherunion.data.dao

import com.areeb.weatherunion.data.database.dao.LocalityDao
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import me.tatarka.inject.annotations.Inject

@Inject
class FakeLocalityDaoImpl : LocalityDao {
    companion object {
        private val defaultLocalityList: List<LocalityData> = listOf(
            LocalityData(
                localityId = "ZWL004494",
                cityName = "Mumbai",
                localityName = "Ghatkopar East, Mumbai",
                latitude = 19.080794,
                longitude = 72.92228,
                deviceType = 1,
            ),
            LocalityData(
                localityId = "ZWL007667",
                cityName = "Mumbai",
                localityName = "Andheri West",
                latitude = 19.137106,
                longitude = 72.834828,
                deviceType = 1,
            ),
            LocalityData(
                localityId = "ZWL005375",
                cityName = "Bengaluru",
                localityName = "Vijayanagar",
                latitude = 12.973219,
                longitude = 77.519303,
                deviceType = 1,
            ),
            LocalityData(
                localityId = "ZWL007633",
                cityName = "Bengaluru",
                localityName = "Whitefield",
                latitude = 12.975224,
                longitude = 77.740422,
                deviceType = 1,
            ),
        )
    }

    private var localityList: List<LocalityData> = emptyList()

    override fun insertLocalities() {
        localityList = defaultLocalityList
    }

    override fun getLocalityList(): List<LocalityData> {
        return localityList
    }

    override fun getLocalitiesMap(): Map<String, List<LocalityData>> {
        return localityList
            .groupBy { it.cityName }
    }
}
