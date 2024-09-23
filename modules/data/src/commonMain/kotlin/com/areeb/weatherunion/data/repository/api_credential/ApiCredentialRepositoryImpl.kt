package com.areeb.weatherunion.data.repository.api_credential

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
}
