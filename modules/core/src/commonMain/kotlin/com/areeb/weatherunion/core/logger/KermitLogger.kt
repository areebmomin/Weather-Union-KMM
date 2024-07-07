package com.areeb.weatherunion.core.logger

import co.touchlab.kermit.DefaultFormatter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.areeb.weatherunion.core.logger.Logger as CoreLogger

class KermitLogger : CoreLogger {

    private val logger = Logger(
        tag = "WeatherUnionKMM",
        config = StaticConfig(
            logWriterList = listOf(
                platformLogWriter(DefaultFormatter),
            ),
        ),
    )

    override fun debug(tag: String, message: String, throwable: Throwable?) {
        logger.d(tag = tag, messageString = message, throwable = throwable)
    }

    override fun assert(tag: String, message: String, throwable: Throwable?) {
        logger.a(tag = tag, messageString = message, throwable = throwable)
    }

    override fun info(tag: String, message: String, throwable: Throwable?) {
        logger.i(tag = tag, messageString = message, throwable = throwable)
    }

    override fun error(tag: String, message: String?, throwable: Throwable?) {
        logger.e(tag = tag, messageString = message ?: "Error", throwable = throwable)
    }

    override fun warn(tag: String, message: String, throwable: Throwable?) {
        logger.w(tag = tag, messageString = message, throwable = throwable)
    }
}
