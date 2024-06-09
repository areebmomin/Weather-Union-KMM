package com.areeb.weatherunion.core.network

import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse

class HttpException(
    response: HttpResponse,
    cachedResponseText: String,
    failureReason: String,
) : ResponseException(response, cachedResponseText) {
    override val message: String = "${response.status.value} - $failureReason"
}
