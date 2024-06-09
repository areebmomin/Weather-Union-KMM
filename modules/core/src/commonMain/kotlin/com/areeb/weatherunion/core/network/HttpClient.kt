package com.areeb.weatherunion.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

@OptIn(ExperimentalSerializationApi::class)
val networkJsonParser = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
    explicitNulls = false
    encodeDefaults = true
    coerceInputValues = true
}
