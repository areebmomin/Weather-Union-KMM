package com.areeb.weatherunion.logic.di

import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import com.areeb.weatherunion.logic.home_screen.viewmodel.HomeScreenViewModel
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel

interface LogicComponent {

    val apiCredentialScreenViewModelFactory: ApiCredentialScreenViewModel.Companion.Factory

    val homeScreenViewModelFactory: HomeScreenViewModel.Companion.Factory

    val locationDataScreenViewModelFactory: LocationDataScreenViewModel.Companion.Factory
}
