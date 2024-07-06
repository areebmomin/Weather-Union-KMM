package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.data.database.AndroidDatabaseDriverFactory
import com.areeb.weatherunion.data.database.DatabaseDriverFactory
import me.tatarka.inject.annotations.Provides

interface AndroidDataComponent {

    val AndroidDatabaseDriverFactory.bind: DatabaseDriverFactory
        @Provides get() = this
}
