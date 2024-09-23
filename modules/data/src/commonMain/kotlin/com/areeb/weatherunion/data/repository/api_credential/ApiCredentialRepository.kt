package com.areeb.weatherunion.data.repository.api_credential

interface ApiCredentialRepository {
    fun updateWeatherUnionApiKey(apiKey: String)
    fun getWeatherUnionApiKey(): String?
}
