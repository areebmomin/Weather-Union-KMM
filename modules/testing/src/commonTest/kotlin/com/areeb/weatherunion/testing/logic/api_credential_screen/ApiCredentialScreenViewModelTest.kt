package com.areeb.weatherunion.testing.logic.api_credential_screen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import com.areeb.weatherunion.logic.api_credential_screen.UpdateWeatherUnionApiKey
import com.areeb.weatherunion.testing.logic.di.createTestApplicationComponent
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiCredentialScreenViewModelTest {
    @Test
    fun `Ensure Weather Union API key is initially empty`() {
        val testApplicationComponent = createTestApplicationComponent()
        val viewModel: ApiCredentialScreenViewModel = ViewModelProvider.create(
            ViewModelStore(),
            testApplicationComponent.apiCredentialScreenViewModelFactory
        )[ApiCredentialScreenViewModel::class]

        assertEquals("", viewModel.latestState.weatherUnionApiKey)
    }

    @Test
    fun `Ensure Weather Union API key is updated`() {
        val testApplicationComponent = createTestApplicationComponent()
        val viewModel: ApiCredentialScreenViewModel = ViewModelProvider.create(
            ViewModelStore(),
            testApplicationComponent.apiCredentialScreenViewModelFactory
        )[ApiCredentialScreenViewModel::class]

        assertEquals("", viewModel.latestState.weatherUnionApiKey)

        viewModel.dispatch(UpdateWeatherUnionApiKey(apiKey = "sg5@fg549x"))

        assertEquals("sg5@fg549x", viewModel.latestState.weatherUnionApiKey)
    }
}