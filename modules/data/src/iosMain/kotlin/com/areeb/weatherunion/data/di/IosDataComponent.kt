package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.data.database.DatabaseDriverFactory
import com.areeb.weatherunion.data.database.IosDatabaseDriverFactory
import com.areeb.weatherunion.data.preference.DataStorePlatformValuesGetter
import com.areeb.weatherunion.data.preference.IosDataStorePlatformValueGetter
import me.tatarka.inject.annotations.Provides

interface IosDataComponent {

    val IosDatabaseDriverFactory.binds: DatabaseDriverFactory
        @ApplicationScope @Provides get() = this

    val IosDataStorePlatformValueGetter.binds: DataStorePlatformValuesGetter
        @ApplicationScope @Provides get() = this
}
