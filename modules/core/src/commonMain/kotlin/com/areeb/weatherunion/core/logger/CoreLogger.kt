package com.areeb.weatherunion.core.logger

interface CoreLogger {
    fun debug(tag: String, message: String, throwable: Throwable? = null)
    fun assert(tag: String, message: String, throwable: Throwable? = null)
    fun info(tag: String, message: String, throwable: Throwable? = null)
    fun error(tag: String, message: String? = null, throwable: Throwable? = null)
    fun warn(tag: String, message: String, throwable: Throwable? = null)
}
