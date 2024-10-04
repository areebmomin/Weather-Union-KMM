package com.areeb.weatherunion.testing.logic.di

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.testing.core.di.TestCoreComponent
import com.areeb.weatherunion.testing.data.di.TestDataComponent
import me.tatarka.inject.annotations.Component

@ApplicationScope
@Component
abstract class TestApplicationComponent : TestCoreComponent, TestDataComponent, TestLogicComponent {
    companion object
}

expect fun createTestApplicationComponent(): TestApplicationComponent
