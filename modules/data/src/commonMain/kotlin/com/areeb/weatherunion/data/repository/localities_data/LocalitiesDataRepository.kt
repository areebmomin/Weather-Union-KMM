package com.areeb.weatherunion.data.repository.localities_data

import com.areeb.weatherunion.data.locality_data.model.LocalityData

interface LocalitiesDataRepository {
    suspend fun insertLocalityDataInDB()
    suspend fun getLocalitiesMap(): Map<LocalityData, List<LocalityData>>
    suspend fun setLastSelectedLocalityId(localityId: String)
    suspend fun getLastSelectedLocalityId(): String
}
