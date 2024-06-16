package com.areeb.weatherunion.data.database

import app.cash.sqldelight.db.SqlDriver
import com.areeb.weatherunion.data.WeatherUnionDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): WeatherUnionDatabase {
    val driver = driverFactory.createDriver()
    val database = WeatherUnionDatabase(driver)

    // Do more work with the database (see below).

    return database
}
