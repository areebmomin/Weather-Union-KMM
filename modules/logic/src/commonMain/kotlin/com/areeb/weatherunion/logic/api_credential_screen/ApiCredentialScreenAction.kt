package com.areeb.weatherunion.logic.api_credential_screen

sealed interface ApiCredentialScreenAction

data class UpdateWeatherUnionApiKey(val apiKey: String) : ApiCredentialScreenAction

data class UpdateMapApiKey(val apiKey: String) : ApiCredentialScreenAction

data object SaveWeatherUnionApiKey : ApiCredentialScreenAction

data object SaveMapApiKey : ApiCredentialScreenAction
