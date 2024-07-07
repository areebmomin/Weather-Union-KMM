package com.areeb.weatherunion.data.database.dao

import com.areeb.weatherunion.data.ApiKeyQueries
import me.tatarka.inject.annotations.Inject

interface ApiKeyDao {
    fun getWeatherUnionApiKey(): String
    fun updateWeatherUnionApiKey(apiKey: String)
    fun getMapApiKey(): String
    fun updateMapApiKey(apiKey: String)
}

@Inject
class ApiKeyDaoImpl(private val apiKeyQueries: ApiKeyQueries) : ApiKeyDao {
    override fun getWeatherUnionApiKey(): String {
        return apiKeyQueries.getWeatherUnionApiKey().executeAsOne().api_key
    }

    override fun updateWeatherUnionApiKey(apiKey: String) {
        apiKeyQueries.insertWeatherUnionApiKey(apiKey = apiKey)
    }

    override fun getMapApiKey(): String {
        return apiKeyQueries.getMapApiKey().executeAsOne().api_key
    }

    override fun updateMapApiKey(apiKey: String) {
        apiKeyQueries.insertMapApiKey(apiKey = apiKey)
    }
}
