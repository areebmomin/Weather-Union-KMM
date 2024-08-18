package com.areeb.weatherunion.core.network.http_client

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

actual fun getWeatherUnionHttpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }

    config(this)
}
