package com.areeb.weatherunion.core.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

object AppCoroutineDispatchers {
    val coroutineDispatchers: CoroutineDispatchers = CoroutineDispatchers(
        main = Dispatchers.Main,
        mainImmediate = Dispatchers.Main.immediate,
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
    )
}
