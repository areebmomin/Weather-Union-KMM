package com.areeb.weatherunion.core.network.http_client

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

actual fun getWeatherUnionHttpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(0, TimeUnit.SECONDS)
        }
    }

    config(this)
}
