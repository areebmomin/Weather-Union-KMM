package com.areeb.weatherunion.testing.logic.di

import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.testing.core.di.TestCoreComponent
import com.areeb.weatherunion.testing.data.di.TestDataComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@ApplicationScope
@Component
abstract class TestApplicationComponent(
    @get:Provides val coroutineDispatchers: CoroutineDispatchers,
) : TestCoreComponent, TestDataComponent, TestLogicComponent {
    companion object
}

expect fun createTestApplicationComponent(coroutineDispatchers: CoroutineDispatchers): TestApplicationComponent
