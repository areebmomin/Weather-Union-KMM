package com.areeb.weatherunion.testing.core.coroutines

import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import kotlinx.coroutines.test.TestDispatcher

fun createTestDispatchers(testDispatcher: TestDispatcher) = CoroutineDispatchers(
    main = testDispatcher,
    mainImmediate = testDispatcher,
    io = testDispatcher,
    computation = testDispatcher,
)
