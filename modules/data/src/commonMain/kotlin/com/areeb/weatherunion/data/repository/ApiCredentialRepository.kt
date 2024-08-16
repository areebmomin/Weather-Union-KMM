package com.areeb.weatherunion.data.repository

interface ApiCredentialRepository {
    fun updateWeatherUnionApiKey(apiKey: String)
    fun getWeatherUnionApiKey(): String?
    fun updateMapApiKey(apiKey: String)
    fun getMapApiKey(): String?
}
