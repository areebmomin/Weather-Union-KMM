package com.areeb.weatherunion.testing.data.di

import com.areeb.weatherunion.data.repository.api_credential.ApiCredentialRepository
import com.areeb.weatherunion.data.repository.api_credential.ApiCredentialRepositoryImpl
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepository
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepositoryImpl
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepository
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepositoryImpl
import me.tatarka.inject.annotations.Provides

interface TestRepositoryComponent {
    val ApiCredentialRepositoryImpl.binds: ApiCredentialRepository
        @Provides get() = this

    val LocalitiesDataRepositoryImpl.binds: LocalitiesDataRepository
        @Provides get() = this

    val WeatherDataRepositoryImpl.binds: WeatherDataRepository
        @Provides get() = this
}
