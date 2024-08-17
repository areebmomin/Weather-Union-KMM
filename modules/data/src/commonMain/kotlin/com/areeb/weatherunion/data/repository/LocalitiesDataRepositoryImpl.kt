package com.areeb.weatherunion.data.repository

import com.areeb.weatherunion.core.util.DEFAULT_LOCALITY_ID
import com.areeb.weatherunion.data.database.dao.LocalityDao
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import kotlinx.coroutines.flow.firstOrNull
import me.tatarka.inject.annotations.Inject

@Inject
class LocalitiesDataRepositoryImpl(
    private val weatherUnionPreferences: WeatherUnionPreferences,
    private val localityDao: LocalityDao,
) : LocalitiesDataRepository {
    override suspend fun insertLocalityDataInDB() {
        localityDao.insertLocalities()
    }

    override suspend fun getLocalityList(): List<LocalityData> {
        return localityDao.getLocalityList()
    }

    override suspend fun setLastSelectedLocalityId(localityId: String) {
        weatherUnionPreferences.updateLastSelectedLocalityId(localityId = localityId)
    }

    override suspend fun getLastSelectedLocalityId(): String {
        val lastSelectedLocalityId =
            weatherUnionPreferences.getLastSelectedLocalityId().firstOrNull()
        return if (lastSelectedLocalityId.isNullOrBlank()) DEFAULT_LOCALITY_ID
        else lastSelectedLocalityId
    }
}
