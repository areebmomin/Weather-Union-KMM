package com.areeb.weatherunion.data.repository

import com.areeb.weatherunion.data.locality_data.model.LocalityData

interface LocalitiesDataRepository {
    suspend fun insertLocalityDataInDB()
    suspend fun getLocalityList(): List<LocalityData>
    suspend fun setLastSelectedLocalityId(localityId: String)
    suspend fun getLastSelectedLocalityId(): String
}
