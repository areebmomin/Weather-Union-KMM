package com.areeb.weatherunion.data.repository

import com.areeb.weatherunion.data.database.dao.ApiKeyDao
import me.tatarka.inject.annotations.Inject

@Inject
class ApiCredentialRepositoryImpl(private val apiKeyDao: ApiKeyDao) : ApiCredentialRepository {
    override fun updateWeatherUnionApiKey(apiKey: String) {
        apiKeyDao.updateWeatherUnionApiKey(apiKey = apiKey)
    }

    override fun getWeatherUnionApiKey(): String? {
        return apiKeyDao.getWeatherUnionApiKey()
    }

    override fun updateMapApiKey(apiKey: String) {
        apiKeyDao.updateMapApiKey(apiKey = apiKey)
    }

    override fun getMapApiKey(): String? {
        return apiKeyDao.getMapApiKey()
    }
}
