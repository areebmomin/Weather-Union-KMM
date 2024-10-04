package com.areeb.weatherunion.testing.core.logger

import com.areeb.weatherunion.core.logger.CoreLogger
import me.tatarka.inject.annotations.Inject

@Inject
class TestLogger : CoreLogger {
    override fun debug(tag: String, message: String, throwable: Throwable?) {}

    override fun assert(tag: String, message: String, throwable: Throwable?) {}

    override fun info(tag: String, message: String, throwable: Throwable?) {}

    override fun error(tag: String, message: String?, throwable: Throwable?) {}

    override fun warn(tag: String, message: String, throwable: Throwable?) {}
}
