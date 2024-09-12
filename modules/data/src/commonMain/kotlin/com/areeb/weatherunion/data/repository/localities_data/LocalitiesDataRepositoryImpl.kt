package com.areeb.weatherunion.data.repository.localities_data

import com.areeb.weatherunion.data.database.dao.LocalityDao
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import com.areeb.weatherunion.data.utils.DEFAULT_LOCALITY
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

    override suspend fun getLocalitiesMap(): Map<String, List<LocalityData>> {
        return localityDao.getLocalitiesMap()
    }

    override suspend fun setLastSelectedLocality(locality: LocalityData) {
        weatherUnionPreferences.updateLastSelectedLocality(locality = locality)
    }

    override suspend fun getLastSelectedLocality(): LocalityData {
        val lastSelectedLocality =
            weatherUnionPreferences.getLastSelectedLocality().firstOrNull()
        return lastSelectedLocality ?: DEFAULT_LOCALITY
    }
}
