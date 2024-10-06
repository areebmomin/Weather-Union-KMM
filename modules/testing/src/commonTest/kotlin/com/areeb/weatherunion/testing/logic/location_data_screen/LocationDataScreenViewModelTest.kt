package com.areeb.weatherunion.testing.logic.location_data_screen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import app.cash.turbine.turbineScope
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.utils.DEFAULT_LOCALITY
import com.areeb.weatherunion.logic.location_data_screen.Error
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenState
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import com.areeb.weatherunion.logic.location_data_screen.OnLocalitySelected
import com.areeb.weatherunion.logic.models.WeatherData
import com.areeb.weatherunion.logic.models.WeatherDataHumidity
import com.areeb.weatherunion.logic.models.WeatherDataRainAccumulation
import com.areeb.weatherunion.logic.models.WeatherDataRainIntensity
import com.areeb.weatherunion.logic.models.WeatherDataTemperature
import com.areeb.weatherunion.logic.models.WeatherDataWindDirection
import com.areeb.weatherunion.logic.models.WeatherDataWindSpeed
import com.areeb.weatherunion.testing.core.coroutines.createTestDispatchers
import com.areeb.weatherunion.testing.data.api.lat_lon_weather_data.FakeLatLonWeatherDataApiImpl
import com.areeb.weatherunion.testing.data.dao.FakeLocalityDaoImpl
import com.areeb.weatherunion.testing.logic.di.createTestApplicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.Test
import kotlin.test.assertEquals

class LocationDataScreenViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Ensure initial Locality and Weather data is fetched properly`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val testApplicationComponent = createTestApplicationComponent(
            coroutineDispatchers = createTestDispatchers(testDispatcher = testDispatcher)
        )
        val weatherData = WeatherData(
            temperature = WeatherDataTemperature(
                temperature = "20",
                unit = "\u2103",
            ),
            humidity = WeatherDataHumidity(
                humidity = "12",
                unit = "%",
            ),
            windSpeed = WeatherDataWindSpeed(
                windSpeed = "5.4",
                unit = " km/h",
            ),
            windDirection = WeatherDataWindDirection(
                windDirection = "NE",
                windDirectionDegree = 34.0f,
                error = "",
            ),
            rainIntensity = WeatherDataRainIntensity(
                rainIntensity = "12.4",
                unit = " mm/min",
            ),
            rainAccumulation = WeatherDataRainAccumulation(
                rainAccumulation = "5.5",
                unit = " mm",
            ),
            deviceDescription = "Data collected using AWS (Automated Weather Station)",
        )

        Dispatchers.setMain(testDispatcher)

        val viewModel: LocationDataScreenViewModel = ViewModelProvider.create(
            ViewModelStore(),
            testApplicationComponent.locationDataScreenViewModelFactory,
        )[LocationDataScreenViewModel::class]

        turbineScope {
            val stateTurbine = viewModel.state.testIn(backgroundScope)

            assertEquals(
                LocationDataScreenState(isLoading = false),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                LocationDataScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = WeatherData(),
                    localityList = FakeLocalityDaoImpl.defaultLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                LocationDataScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = weatherData,
                    localityList = FakeLocalityDaoImpl.defaultLocalityList,
                ),
                stateTurbine.awaitItem(),
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Ensure Locality data is fetched properly when Locality is selected`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val testApplicationComponent = createTestApplicationComponent(
            coroutineDispatchers = createTestDispatchers(testDispatcher = testDispatcher)
        )
        val weatherData = WeatherData(
            temperature = WeatherDataTemperature(
                temperature = "20",
                unit = "\u2103",
            ),
            humidity = WeatherDataHumidity(
                humidity = "12",
                unit = "%",
            ),
            windSpeed = WeatherDataWindSpeed(
                windSpeed = "5.4",
                unit = " km/h",
            ),
            windDirection = WeatherDataWindDirection(
                windDirection = "NE",
                windDirectionDegree = 34.0f,
                error = "",
            ),
            rainIntensity = WeatherDataRainIntensity(
                rainIntensity = "12.4",
                unit = " mm/min",
            ),
            rainAccumulation = WeatherDataRainAccumulation(
                rainAccumulation = "5.5",
                unit = " mm",
            ),
            deviceDescription = "Data collected using AWS (Automated Weather Station)",
        )
        val selectedLocality = LocalityData(
            localityId = "ZWL004494",
            cityName = "Mumbai",
            localityName = "Ghatkopar East, Mumbai",
            latitude = 19.080794,
            longitude = 72.92228,
            deviceType = 1,
        )

        Dispatchers.setMain(testDispatcher)

        val viewModel: LocationDataScreenViewModel = ViewModelProvider.create(
            ViewModelStore(),
            testApplicationComponent.locationDataScreenViewModelFactory,
        )[LocationDataScreenViewModel::class]

        turbineScope {
            val stateTurbine = viewModel.state.testIn(backgroundScope)

            assertEquals(
                LocationDataScreenState(isLoading = false),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                LocationDataScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = WeatherData(),
                    localityList = FakeLocalityDaoImpl.defaultLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                LocationDataScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = weatherData,
                    localityList = FakeLocalityDaoImpl.defaultLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            (testApplicationComponent.latLonWeatherDataApi as FakeLatLonWeatherDataApiImpl)
                .temperature = 20.5

            viewModel.dispatch(OnLocalitySelected(locality = selectedLocality))

            assertEquals(
                LocationDataScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = selectedLocality,
                    weatherData = weatherData,
                    localityList = FakeLocalityDaoImpl.defaultLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                LocationDataScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = selectedLocality,
                    weatherData = weatherData.copy(
                        temperature = weatherData.temperature.copy(temperature = "21"),
                    ),
                    localityList = FakeLocalityDaoImpl.defaultLocalityList,
                ),
                stateTurbine.awaitItem(),
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Ensure error is handled properly when Weather data is fetched for locality selected`() =
        runTest {
            val testDispatcher = StandardTestDispatcher(testScheduler)
            val testApplicationComponent = createTestApplicationComponent(
                coroutineDispatchers = createTestDispatchers(testDispatcher = testDispatcher)
            )
            val weatherData = WeatherData(
                temperature = WeatherDataTemperature(
                    temperature = "20",
                    unit = "\u2103",
                ),
                humidity = WeatherDataHumidity(
                    humidity = "12",
                    unit = "%",
                ),
                windSpeed = WeatherDataWindSpeed(
                    windSpeed = "5.4",
                    unit = " km/h",
                ),
                windDirection = WeatherDataWindDirection(
                    windDirection = "NE",
                    windDirectionDegree = 34.0f,
                    error = "",
                ),
                rainIntensity = WeatherDataRainIntensity(
                    rainIntensity = "12.4",
                    unit = " mm/min",
                ),
                rainAccumulation = WeatherDataRainAccumulation(
                    rainAccumulation = "5.5",
                    unit = " mm",
                ),
                deviceDescription = "Data collected using AWS (Automated Weather Station)",
            )
            val selectedLocality = LocalityData(
                localityId = "ZWL004494",
                cityName = "Mumbai",
                localityName = "Ghatkopar East, Mumbai",
                latitude = 19.080794,
                longitude = 72.92228,
                deviceType = 1,
            )

            Dispatchers.setMain(testDispatcher)

            val viewModel: LocationDataScreenViewModel = ViewModelProvider.create(
                ViewModelStore(),
                testApplicationComponent.locationDataScreenViewModelFactory,
            )[LocationDataScreenViewModel::class]

            turbineScope {
                val stateTurbine = viewModel.state.testIn(backgroundScope)
                val eventTurbine = viewModel.event.testIn(backgroundScope)

                assertEquals(
                    LocationDataScreenState(isLoading = false),
                    stateTurbine.awaitItem(),
                )

                assertEquals(
                    LocationDataScreenState(
                        isLoading = false,
                        isLocalityDataLoading = false,
                        selectedLocality = DEFAULT_LOCALITY,
                        weatherData = WeatherData(),
                        localityList = FakeLocalityDaoImpl.defaultLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                assertEquals(
                    LocationDataScreenState(
                        isLoading = false,
                        isLocalityDataLoading = false,
                        selectedLocality = DEFAULT_LOCALITY,
                        weatherData = weatherData,
                        localityList = FakeLocalityDaoImpl.defaultLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                (testApplicationComponent.latLonWeatherDataApi as FakeLatLonWeatherDataApiImpl)
                    .returnSuccess = false

                viewModel.dispatch(OnLocalitySelected(locality = selectedLocality))

                assertEquals(
                    LocationDataScreenState(
                        isLoading = false,
                        isLocalityDataLoading = false,
                        selectedLocality = selectedLocality,
                        weatherData = weatherData,
                        localityList = FakeLocalityDaoImpl.defaultLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                assertEquals(Error(message = "Something went wrong"), eventTurbine.awaitItem())

                assertEquals(
                    LocationDataScreenState(
                        isLoading = false,
                        isLocalityDataLoading = false,
                        selectedLocality = selectedLocality,
                        weatherData = WeatherData(),
                        localityList = FakeLocalityDaoImpl.defaultLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )
            }
        }
}
