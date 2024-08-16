package com.areeb.weatherunion.logic.di

import androidx.lifecycle.ViewModelProvider
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import com.areeb.weatherunion.logic.home_screen.HomeScreenViewModel
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import kotlin.reflect.KClass

typealias ViewModelFactory = ViewModelProvider.Factory

interface LogicComponent {

    val viewModelFactories: Map<KClass<*>, ViewModelFactory>

    @IntoMap
    @Provides
    fun apiCredentialScreenViewModelFactory(
        factory: ApiCredentialScreenViewModel.Companion.Factory,
    ): Pair<KClass<*>, ViewModelFactory> =
        Pair(ApiCredentialScreenViewModel::class, factory)

    @IntoMap
    @Provides
    fun homeScreenViewModelFactory(
        factory: HomeScreenViewModel.Companion.Factory,
    ): Pair<KClass<*>, ViewModelFactory> = Pair(HomeScreenViewModel::class, factory)

    @IntoMap
    @Provides
    fun locationDataScreenViewModelFactory(
        factory: LocationDataScreenViewModel.Companion.Factory,
    ): Pair<KClass<*>, ViewModelFactory> =
        Pair(LocationDataScreenViewModel::class, factory)
}
