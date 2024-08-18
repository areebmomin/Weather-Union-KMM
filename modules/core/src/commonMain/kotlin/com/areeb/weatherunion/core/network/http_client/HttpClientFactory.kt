package com.areeb.weatherunion.core.network.http_client

import io.ktor.client.HttpClient

interface HttpClientFactory {
    val client: HttpClient
}
