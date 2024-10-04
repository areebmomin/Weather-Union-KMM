package com.areeb.weatherunion.testing.data.di

import com.areeb.weatherunion.testing.data.dao.FakeApiKeyDaoImpl
import com.areeb.weatherunion.testing.data.dao.FakeLocalityDaoImpl
import com.areeb.weatherunion.data.database.dao.ApiKeyDao
import com.areeb.weatherunion.data.database.dao.LocalityDao
import me.tatarka.inject.annotations.Provides

interface TestDatabaseComponent {
    val FakeApiKeyDaoImpl.binds: ApiKeyDao
        @Provides get() = this

    val FakeLocalityDaoImpl.binds: LocalityDao
        @Provides get() = this
}
