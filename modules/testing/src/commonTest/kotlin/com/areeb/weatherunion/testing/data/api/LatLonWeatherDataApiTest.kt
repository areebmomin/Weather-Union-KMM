package com.areeb.weatherunion.testing.data.api

import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.network.HttpException
import com.areeb.weatherunion.core.network.http_client.HttpClientFactory
import com.areeb.weatherunion.data.api.lat_lon_weather_data.LatLonWeatherDataApiImpl
import com.areeb.weatherunion.testing.data.dao.FakeApiKeyDaoImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class LatLonWeatherDataApiTest {

    private val testApiKey = "dbkcgm1m5biz59c6"

    @Test
    fun `test getWeatherData returns success response`() = runTest {
        val mockEngine = MockEngine { request ->
            assertEquals("/gw/weather/external/v0/get_weather_data", request.url.encodedPath)
            assertEquals(HttpMethod.Get, request.method)
            assertEquals(testApiKey, request.headers["x-zomato-api-key"])
            assertEquals("12.34", request.url.parameters["latitude"])
            assertEquals("56.78", request.url.parameters["longitude"])

            respond(
                content = ByteReadChannel(
                    """
                    {
                        "status": “200”,
                        "message": "",
                        "device_type": 1,
                        "locality_weather_data": {
                            "temperature": 25.68,
                            "humidity": 25.81,
                            "wind_speed": 1.15,
                            "wind_direction": 331.2,
                            "rain_intensity": 0,
                            "rain_accumulation": 0.4
                        }
                    }
                    """.trimIndent()
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    Json.toString()
                )
            )
        }

        val testHttpClientFactory = object : HttpClientFactory {
            override val client: HttpClient = HttpClient(mockEngine) {
                expectSuccess = true

                defaultRequest {
                    contentType(Json)
                    url("https://www.weatherunion.com/gw/weather/external/v0/")
                }

                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                            explicitNulls = false
                            encodeDefaults = true
                            coerceInputValues = true
                        },
                    )
                }

                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }

        val apiKeyDao = FakeApiKeyDaoImpl()
        apiKeyDao.updateWeatherUnionApiKey(apiKey = testApiKey)

        val api = LatLonWeatherDataApiImpl(testHttpClientFactory, apiKeyDao)

        val response = api.getWeatherData(12.34f, 56.78f)
        println(response)
        when (response) {
            is ApiResponse.Error.GenericError -> {
                assertEquals("Data must be fetched successfully", "GenericError")
            }

            is ApiResponse.Error.HttpError -> {
                assertEquals("Data must be fetched successfully", "HttpError")
            }

            is ApiResponse.Error.SerializationError -> {
                assertEquals("Data must be fetched successfully", "SerializationError")
            }

            is ApiResponse.Success -> {
                val responseBody = response.body
                val weatherData = responseBody.localityWeatherData

                assertEquals(1, responseBody.deviceType)
                assertEquals(25.68, weatherData?.temperature)
                assertEquals(25.81, weatherData?.humidity)
                assertEquals(1.15, weatherData?.windSpeed)
                assertEquals(331.2, weatherData?.windDirection)
                assertEquals(0.0, weatherData?.rainIntensity)
                assertEquals(0.4, weatherData?.rainAccumulation)
            }
        }
    }

    @Test
    fun `test getWeatherData returns error response`() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    """
                    {
                        "status": "error",
                        "message": "Invalid latitude/longitude"
                    }
                    """.trimIndent()
                ),
                status = HttpStatusCode.BadRequest,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    Json.toString()
                )
            )
        }

        val testHttpClientFactory = object : HttpClientFactory {
            override val client: HttpClient = HttpClient(mockEngine) {
                expectSuccess = true

                defaultRequest {
                    contentType(Json)
                    url("https://www.weatherunion.com/gw/weather/external/v0/")
                }

                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                            explicitNulls = false
                            encodeDefaults = true
                            coerceInputValues = true
                        },
                    )
                }

                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }

        val apiKeyDao = FakeApiKeyDaoImpl()
        apiKeyDao.updateWeatherUnionApiKey(apiKey = testApiKey)

        val api = LatLonWeatherDataApiImpl(testHttpClientFactory, apiKeyDao)

        try {
            val response =  api.getWeatherData(12.34f, 56.78f)
            println(response)
        } catch (e: HttpException) {
            assertEquals("Empty or invalid latitude/longitude given in params", e.message)
        }
    }
}
