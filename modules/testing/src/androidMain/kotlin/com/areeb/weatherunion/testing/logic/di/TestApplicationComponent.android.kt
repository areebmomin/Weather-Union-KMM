package com.areeb.weatherunion.testing.logic.di

import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers

actual fun createTestApplicationComponent(coroutineDispatchers: CoroutineDispatchers): TestApplicationComponent =
    TestApplicationComponent.create()
