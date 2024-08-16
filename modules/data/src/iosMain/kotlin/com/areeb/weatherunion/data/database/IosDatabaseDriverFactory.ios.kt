package com.areeb.weatherunion.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.areeb.weatherunion.data.WeatherUnionDatabase
import me.tatarka.inject.annotations.Inject

@Inject
class IosDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(WeatherUnionDatabase.Schema, "weather_union.db")
    }
}
