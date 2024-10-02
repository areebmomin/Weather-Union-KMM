package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.data.preference.FakeWeatherUnionPreferenceImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import me.tatarka.inject.annotations.Provides

interface TestPreferenceComponent {
    val FakeWeatherUnionPreferenceImpl.binds: WeatherUnionPreferences
        @ApplicationScope @Provides get() = this
}
