package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.data.ApiKeyQueries
import com.areeb.weatherunion.data.LocalityQueries
import com.areeb.weatherunion.data.WeatherUnionDatabase
import com.areeb.weatherunion.data.database.DatabaseFactory
import com.areeb.weatherunion.data.database.dao.ApiKeyDao
import com.areeb.weatherunion.data.database.dao.ApiKeyDaoImpl
import com.areeb.weatherunion.data.database.dao.LocalityDao
import com.areeb.weatherunion.data.database.dao.LocalityDaoImpl
import me.tatarka.inject.annotations.Provides

interface DatabaseComponent {

    val DatabaseFactory.binds: WeatherUnionDatabase
        @ApplicationScope @Provides get() = this.createDatabase()

    val WeatherUnionDatabase.bindApiKeyQueries: ApiKeyQueries
        @Provides get() = this.apiKeyQueries

    val WeatherUnionDatabase.bindLocalityQueries: LocalityQueries
        @Provides get() = this.localityQueries

    val ApiKeyDaoImpl.binds: ApiKeyDao
        @Provides get() = this

    val LocalityDaoImpl.binds: LocalityDao
        @Provides get() = this
}
