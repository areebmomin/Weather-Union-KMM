package com.areeb.weatherunion.data.repository

import com.areeb.weatherunion.data.database.dao.LocalityDao
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import kotlinx.coroutines.flow.Flow
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

    override suspend fun setLastSelectedLocality(localityData: LocalityData) {
        weatherUnionPreferences.updateLastSelectedLocalityId(localityId = localityData.localityId)
    }

    override fun getLastSelectedLocality(): Flow<String?> {
        return weatherUnionPreferences.getLastSelectedLocalityId()
    }
}
