package com.areeb.weatherunion.logic.api_credential_screen

sealed interface ApiCredentialScreenEvent

data class ShowSnackBar(val message: String) : ApiCredentialScreenEvent
