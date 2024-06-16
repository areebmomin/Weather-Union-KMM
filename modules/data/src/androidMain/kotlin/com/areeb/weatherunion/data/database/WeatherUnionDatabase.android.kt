package com.areeb.weatherunion.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.areeb.weatherunion.data.WeatherUnionDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(WeatherUnionDatabase.Schema, context, "weather_union.db")
    }
}
