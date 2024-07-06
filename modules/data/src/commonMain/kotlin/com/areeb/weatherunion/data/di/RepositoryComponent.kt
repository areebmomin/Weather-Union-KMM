package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.data.repository.ApiCredentialRepository
import com.areeb.weatherunion.data.repository.ApiCredentialRepositoryImpl
import com.areeb.weatherunion.data.repository.LocalitiesDataRepository
import com.areeb.weatherunion.data.repository.LocalitiesDataRepositoryImpl
import com.areeb.weatherunion.data.repository.WeatherDataRepository
import com.areeb.weatherunion.data.repository.WeatherDataRepositoryImpl
import me.tatarka.inject.annotations.Provides

interface RepositoryComponent {

    val ApiCredentialRepositoryImpl.binds: ApiCredentialRepository
        @Provides get() = this

    val LocalitiesDataRepositoryImpl.binds: LocalitiesDataRepository
        @Provides get() = this

    val WeatherDataRepositoryImpl.binds: WeatherDataRepository
        @Provides get() = this
}
