package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.data.preference.ProtoDataStoreFactory
import com.areeb.weatherunion.data.preference.ProtoDataStoreFactoryImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferenceImpl
import com.areeb.weatherunion.data.preference.WeatherUnionPreferences
import me.tatarka.inject.annotations.Provides

interface PreferenceComponent {

    val ProtoDataStoreFactoryImpl.binds: ProtoDataStoreFactory
        @ApplicationScope @Provides get() = this

    val WeatherUnionPreferenceImpl.binds: WeatherUnionPreferences
        @ApplicationScope @Provides get() = this
}
