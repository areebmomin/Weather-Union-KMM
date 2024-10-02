package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.data.api.lat_lon_weather_data.FakeLatLonWeatherDataApiImpl
import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApi
import com.areeb.weatherunion.data.api.locality_weather_data.FakeLocalityWeatherDataApiImpl
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApi
import me.tatarka.inject.annotations.Provides

interface TestApiComponent {
    @Provides
    fun latLonWeatherDataApi(impl: FakeLatLonWeatherDataApiImpl): LatLonWeatherDataApi = impl

    @Provides
    fun localityWeatherDataApi(impl: FakeLocalityWeatherDataApiImpl): LocalityWeatherDataApi = impl
}
