package com.areeb.weatherunion.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutineDispatchers(
    val main: CoroutineDispatcher,
    val mainImmediate: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
)
