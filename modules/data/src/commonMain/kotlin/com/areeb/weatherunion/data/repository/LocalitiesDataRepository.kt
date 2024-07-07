package com.areeb.weatherunion.data.repository

import com.areeb.weatherunion.data.locality_data.model.LocalityData
import kotlinx.coroutines.flow.Flow

interface LocalitiesDataRepository {
    suspend fun insertLocalityDataInDB()
    suspend fun getLocalityList(): List<LocalityData>
    suspend fun setLastSelectedLocality(localityData: LocalityData)
    fun getLastSelectedLocality(): Flow<String?>
}
