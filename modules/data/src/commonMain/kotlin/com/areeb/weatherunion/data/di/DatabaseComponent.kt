package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.data.WeatherUnionDatabase
import com.areeb.weatherunion.data.database.DatabaseFactory
import me.tatarka.inject.annotations.Provides

interface DatabaseComponent {

    val DatabaseFactory.binds: WeatherUnionDatabase
        @Provides get() = this.createDatabase()
}
