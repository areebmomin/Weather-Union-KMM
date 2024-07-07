package com.areeb.weatherunion.core.coroutines

import kotlinx.coroutines.Dispatchers

actual object AppCoroutineDispatchers {
    actual val coroutineDispatchers: CoroutineDispatchers = CoroutineDispatchers(
        main = Dispatchers.Main,
        mainImmediate = Dispatchers.Main.immediate,
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
    )
}
