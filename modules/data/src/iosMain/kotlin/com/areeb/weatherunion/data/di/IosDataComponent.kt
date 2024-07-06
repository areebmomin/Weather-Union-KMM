package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.data.database.DatabaseDriverFactory
import com.areeb.weatherunion.data.database.IosDatabaseDriverFactory
import me.tatarka.inject.annotations.Provides

interface IosDataComponent {

    val IosDatabaseDriverFactory.binds: DatabaseDriverFactory
        @Provides get() = this
}
