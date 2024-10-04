package com.areeb.weatherunion.testing.data.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.testing.data.api.lat_lon_weather_data.FakeLatLonWeatherDataApiImpl
import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApi
import com.areeb.weatherunion.testing.data.api.locality_weather_data.FakeLocalityWeatherDataApiImpl
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApi
import me.tatarka.inject.annotations.Provides

interface TestApiComponent {

    val latLonWeatherDataApi: LatLonWeatherDataApi
    val localityWeatherDataApi: LocalityWeatherDataApi

    @ApplicationScope
    @Provides
    fun latLonWeatherDataApi(impl: FakeLatLonWeatherDataApiImpl): LatLonWeatherDataApi = impl

    @ApplicationScope
    @Provides
    fun localityWeatherDataApi(impl: FakeLocalityWeatherDataApiImpl): LocalityWeatherDataApi = impl
}
