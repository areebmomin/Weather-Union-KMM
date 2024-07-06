package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApi
import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApiImpl
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApi
import com.areeb.weatherunion.data.api.locality_weather_data.LocalityWeatherDataApiImpl
import me.tatarka.inject.annotations.Provides

interface ApiComponent {

    @Provides
    fun latLonWeatherDataApi(): LatLonWeatherDataApi = LatLonWeatherDataApiImpl()

    @Provides
    fun localityWeatherDataApi(): LocalityWeatherDataApi = LocalityWeatherDataApiImpl()
}
