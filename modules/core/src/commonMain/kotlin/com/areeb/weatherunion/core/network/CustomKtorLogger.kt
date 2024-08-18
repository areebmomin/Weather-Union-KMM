package com.areeb.weatherunion.core.network

import com.areeb.weatherunion.core.logger.CoreLogger
import io.ktor.client.plugins.logging.Logger
import me.tatarka.inject.annotations.Inject

@Inject
class CustomKtorLogger(private val coreLogger: CoreLogger) : Logger {
    override fun log(message: String) {
        coreLogger.debug(tag = "Ktor", message = message)
    }
}
