package com.areeb.weatherunion.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

expect fun httpClient(
    config: HttpClientConfig<*>.() -> Unit = {
        expectSuccess = true

        defaultRequest {
            contentType(ContentType.Application.Json)
            url("https://www.weatherunion.com/gw/weather/external/v0/")
            header("x-zomato-api-key", "API_KEY")
        }

        install(ContentNegotiation) {
            json(networkJsonParser)
        }

        install(Logging) {
            logger = CustomKtorLogger()
            level = LogLevel.ALL
        }

        HttpResponseValidator {
//            TODO: validate 200 response when 'validateResponse{}' issue is fixed
//            validateResponse { response ->
//                when (message) {
//                    "temporarily unavailable" -> throw HttpException(
//                        response = response,
//                        cachedResponseText = response.bodyAsText(),
//                        failureReason = "Unable to fetch data at this point in time for this lat lon",
//                    )
//
//                    "latitude longitude not supported. refer to the api doc for supported areas" -> throw HttpException(
//                        response = response,
//                        cachedResponseText = response.bodyAsText(),
//                        failureReason = "Entered latitude/longitude is not supported",
//                    )
//
//                    else -> {
//                        // ignore
//                    }
//                }
//            }

            handleResponseExceptionWithRequest { exception, request ->
                (exception as? ClientRequestException)?.apply {
                    when (response.status.value) {
                        400 -> {
                            throw HttpException(
                                response = response,
                                cachedResponseText = response.bodyAsText(),
                                failureReason = "Empty or invalid latitude/longitude given in params",
                            )
                        }

                        403 -> {
                            throw HttpException(
                                response = response,
                                cachedResponseText = response.bodyAsText(),
                                failureReason = "Given api key is invalid",
                            )
                        }

                        429 -> {
                            throw HttpException(
                                response = response,
                                cachedResponseText = response.bodyAsText(),
                                failureReason = "Daily api quota for user is already exhausted",
                            )
                        }
                    }
                }
            }
        }
    },
): HttpClient

@OptIn(ExperimentalSerializationApi::class)
val networkJsonParser = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
    explicitNulls = false
    encodeDefaults = true
    coerceInputValues = true
}
