package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.data.preference.WeatherUnionPreferenceImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import me.tatarka.inject.annotations.Provides

interface PreferenceComponent {

    @Provides
    fun weatherUnionPreference(): WeatherUnionPreferences = WeatherUnionPreferenceImpl()
}
