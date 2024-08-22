package com.areeb.weatherunion.data.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.data.database.AndroidDatabaseDriverFactory
import com.areeb.weatherunion.data.database.DatabaseDriverFactory
import com.areeb.weatherunion.data.preference.AndroidDataStorePlatformValueGetter
import com.areeb.weatherunion.data.preference.DataStorePlatformValuesGetter
import me.tatarka.inject.annotations.Provides

interface AndroidDataComponent {

    val AndroidDatabaseDriverFactory.bind: DatabaseDriverFactory
        @ApplicationScope @Provides get() = this

    val AndroidDataStorePlatformValueGetter.binds: DataStorePlatformValuesGetter
        @ApplicationScope @Provides get() = this
}
