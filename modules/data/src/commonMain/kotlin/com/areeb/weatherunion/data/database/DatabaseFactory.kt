package com.areeb.weatherunion.data.database

import com.areeb.weatherunion.data.WeatherUnionDatabase
import me.tatarka.inject.annotations.Inject

@Inject
class DatabaseFactory(private val databaseDriverFactory: DatabaseDriverFactory) {

    fun createDatabase(): WeatherUnionDatabase {
        val driver = databaseDriverFactory.createDriver()
        val database = WeatherUnionDatabase(driver)

        // Do more work with the database (see below).

        return database
    }
}
