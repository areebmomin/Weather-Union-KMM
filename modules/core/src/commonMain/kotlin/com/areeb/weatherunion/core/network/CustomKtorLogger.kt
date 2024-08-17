package com.areeb.weatherunion.core.network

import io.ktor.client.plugins.logging.Logger

class CustomKtorLogger : Logger {
    override fun log(message: String) {
        // Implement your custom logging logic here
        // For example, print to console or send to a remote logging service
        println("Ktor: $message")
    }
}
