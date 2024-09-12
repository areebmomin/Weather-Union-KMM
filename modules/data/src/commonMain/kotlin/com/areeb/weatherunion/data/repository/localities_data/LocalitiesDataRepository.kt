package com.areeb.weatherunion.data.repository.localities_data

import com.areeb.weatherunion.data.locality_data.model.LocalityData

interface LocalitiesDataRepository {
    suspend fun insertLocalityDataInDB()
    suspend fun getLocalitiesMap(): Map<String, List<LocalityData>>
    suspend fun setLastSelectedLocality(locality: LocalityData)
    suspend fun getLastSelectedLocality(): LocalityData
}
