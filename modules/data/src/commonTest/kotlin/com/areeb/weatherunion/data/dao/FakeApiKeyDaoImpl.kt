package com.areeb.weatherunion.data.dao

import com.areeb.weatherunion.data.database.dao.ApiKeyDao
import me.tatarka.inject.annotations.Inject

@Inject
class FakeApiKeyDaoImpl : ApiKeyDao {
    private var weatherUnionApiKey: String? = null

    override fun getWeatherUnionApiKey(): String? {
        return weatherUnionApiKey
    }

    override fun updateWeatherUnionApiKey(apiKey: String) {
        weatherUnionApiKey = apiKey
    }
}
