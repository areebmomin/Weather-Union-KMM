package com.areeb.weatherunion.data.database.dao

import com.areeb.weatherunion.data.ApiKeyQueries
import me.tatarka.inject.annotations.Inject

interface ApiKeyDao {
    fun getWeatherUnionApiKey(): String?
    fun updateWeatherUnionApiKey(apiKey: String)
}

@Inject
class ApiKeyDaoImpl(private val apiKeyQueries: ApiKeyQueries) : ApiKeyDao {
    override fun getWeatherUnionApiKey(): String? {
        return apiKeyQueries.getWeatherUnionApiKey().executeAsOneOrNull()?.api_key
    }

    override fun updateWeatherUnionApiKey(apiKey: String) {
        apiKeyQueries.insertWeatherUnionApiKey(apiKey = apiKey)
    }
}
