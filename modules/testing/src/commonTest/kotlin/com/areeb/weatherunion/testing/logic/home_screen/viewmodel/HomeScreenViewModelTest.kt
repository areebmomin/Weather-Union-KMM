package com.areeb.weatherunion.testing.logic.home_screen.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.utils.DEFAULT_LOCALITY
import com.areeb.weatherunion.logic.home_screen.viewmodel.Error
import com.areeb.weatherunion.logic.home_screen.viewmodel.HomeScreenState
import com.areeb.weatherunion.logic.home_screen.viewmodel.HomeScreenViewModel
import com.areeb.weatherunion.logic.home_screen.viewmodel.OnLocalitySelected
import com.areeb.weatherunion.logic.home_screen.viewmodel.RefreshWeatherData
import com.areeb.weatherunion.logic.models.WeatherData
import com.areeb.weatherunion.logic.models.WeatherDataHumidity
import com.areeb.weatherunion.logic.models.WeatherDataRainAccumulation
import com.areeb.weatherunion.logic.models.WeatherDataRainIntensity
import com.areeb.weatherunion.logic.models.WeatherDataTemperature
import com.areeb.weatherunion.logic.models.WeatherDataWindDirection
import com.areeb.weatherunion.logic.models.WeatherDataWindSpeed
import com.areeb.weatherunion.testing.data.api.locality_weather_data.FakeLocalityWeatherDataApiImpl
import com.areeb.weatherunion.testing.data.dao.FakeLocalityDaoImpl
import com.areeb.weatherunion.testing.logic.di.createTestApplicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeScreenViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Ensure initial Locality and Weather data is fetched properly`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val testApplicationComponent = createTestApplicationComponent()
        val localitiesMap = FakeLocalityDaoImpl.defaultLocalityList.groupBy { it.cityName }
        val uniqueCitiesFirstLocalityList = localitiesMap.values.map { list -> list.first() }
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

        val viewModel: HomeScreenViewModel = ViewModelProvider.create(
            ViewModelStore(),
            testApplicationComponent.homeScreenViewModelFactory
        )[HomeScreenViewModel::class]

        turbineScope {
            val stateTurbine = viewModel.state.testIn(backgroundScope)

            assertEquals(HomeScreenState(), stateTurbine.awaitItem())

            assertEquals(HomeScreenState(isLocalityDataLoading = true), stateTurbine.awaitItem())

            assertEquals(
                HomeScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = WeatherData(),
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                HomeScreenState(
                    isLoading = true,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = WeatherData(),
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                HomeScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = weatherData,
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Ensure Locality data is fetched properly when Locality is selected`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val testApplicationComponent = createTestApplicationComponent()
        val localitiesMap = FakeLocalityDaoImpl.defaultLocalityList.groupBy { it.cityName }
        val uniqueCitiesFirstLocalityList = localitiesMap.values.map { list -> list.first() }
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

        val viewModel: HomeScreenViewModel = ViewModelProvider.create(
            ViewModelStore(),
            testApplicationComponent.homeScreenViewModelFactory
        )[HomeScreenViewModel::class]

        turbineScope {
            val stateTurbine = viewModel.state.testIn(backgroundScope)

            assertEquals(HomeScreenState(), stateTurbine.awaitItem())

            assertEquals(HomeScreenState(isLocalityDataLoading = true), stateTurbine.awaitItem())

            assertEquals(
                HomeScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = WeatherData(),
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                HomeScreenState(
                    isLoading = true,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = WeatherData(),
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                HomeScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = weatherData,
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            (testApplicationComponent.localityWeatherDataApi as FakeLocalityWeatherDataApiImpl)
                .temperature = 20.5

            viewModel.dispatch(OnLocalitySelected(locality = selectedLocality))

            assertEquals(
                HomeScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = selectedLocality,
                    weatherData = weatherData,
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                HomeScreenState(
                    isLoading = true,
                    isLocalityDataLoading = false,
                    selectedLocality = selectedLocality,
                    weatherData = weatherData,
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )

            assertEquals(
                HomeScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = selectedLocality,
                    weatherData = weatherData.copy(
                        temperature = weatherData.temperature.copy(temperature = "21"),
                    ),
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                stateTurbine.awaitItem(),
            )
        }
    }

    @Test
    fun `Ensure Weather data is fetched properly when weather data is refreshed`() = runTest {
        val testApplicationComponent = createTestApplicationComponent()
        val localitiesMap = FakeLocalityDaoImpl.defaultLocalityList.groupBy { it.cityName }
        val uniqueCitiesFirstLocalityList = localitiesMap.values.map { list -> list.first() }
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

        val viewModel: HomeScreenViewModel = ViewModelProvider.create(
            ViewModelStore(),
            testApplicationComponent.homeScreenViewModelFactory
        )[HomeScreenViewModel::class]

        viewModel.state.test {
            awaitItem()
            awaitItem()
            awaitItem()
            awaitItem()

            viewModel.dispatch(RefreshWeatherData)

            assertEquals(
                HomeScreenState(
                    isLoading = true,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = weatherData,
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                awaitItem(),
            )

            assertEquals(
                HomeScreenState(
                    isLoading = false,
                    isLocalityDataLoading = false,
                    selectedLocality = DEFAULT_LOCALITY,
                    weatherData = weatherData,
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `Ensure error is handled properly when Weather data is fetched for locality selected`() =
        runTest {
            val testApplicationComponent = createTestApplicationComponent()
            val localitiesMap = FakeLocalityDaoImpl.defaultLocalityList.groupBy { it.cityName }
            val uniqueCitiesFirstLocalityList = localitiesMap.values.map { list -> list.first() }
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

            turbineScope {
                val viewModel: HomeScreenViewModel = ViewModelProvider.create(
                    ViewModelStore(),
                    testApplicationComponent.homeScreenViewModelFactory
                )[HomeScreenViewModel::class]

                val stateTurbine = viewModel.state.testIn(backgroundScope)
                val eventTurbine = viewModel.event.testIn(backgroundScope)

                stateTurbine.awaitItem()
                stateTurbine.awaitItem()
                stateTurbine.awaitItem()
                stateTurbine.awaitItem()

                (testApplicationComponent.localityWeatherDataApi as FakeLocalityWeatherDataApiImpl)
                    .returnSuccess = false

                viewModel.dispatch(OnLocalitySelected(locality = selectedLocality))

                assertEquals(
                    HomeScreenState(
                        isLoading = false,
                        isLocalityDataLoading = false,
                        selectedLocality = selectedLocality,
                        weatherData = weatherData,
                        localitiesMap = localitiesMap,
                        uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                assertEquals(
                    HomeScreenState(
                        isLoading = true,
                        isLocalityDataLoading = false,
                        selectedLocality = selectedLocality,
                        weatherData = weatherData,
                        localitiesMap = localitiesMap,
                        uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                assertEquals(Error(message = "Something went wrong"), eventTurbine.awaitItem())

                assertEquals(
                    HomeScreenState(
                        isLoading = true,
                        isLocalityDataLoading = false,
                        selectedLocality = selectedLocality,
                        weatherData = WeatherData(),
                        localitiesMap = localitiesMap,
                        uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                assertEquals(
                    HomeScreenState(
                        isLoading = false,
                        isLocalityDataLoading = false,
                        selectedLocality = selectedLocality,
                        weatherData = WeatherData(),
                        localitiesMap = localitiesMap,
                        uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )
            }
        }

    @Test
    fun `Ensure error is handled properly when Weather data is fetched for weather data refresh`() =
        runTest {
            val testApplicationComponent = createTestApplicationComponent()
            val localitiesMap = FakeLocalityDaoImpl.defaultLocalityList.groupBy { it.cityName }
            val uniqueCitiesFirstLocalityList = localitiesMap.values.map { list -> list.first() }
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

            turbineScope {
                val viewModel: HomeScreenViewModel = ViewModelProvider.create(
                    ViewModelStore(),
                    testApplicationComponent.homeScreenViewModelFactory
                )[HomeScreenViewModel::class]

                val stateTurbine = viewModel.state.testIn(backgroundScope)
                val eventTurbine = viewModel.event.testIn(backgroundScope)

                stateTurbine.awaitItem()
                stateTurbine.awaitItem()
                stateTurbine.awaitItem()
                stateTurbine.awaitItem()

                (testApplicationComponent.localityWeatherDataApi as FakeLocalityWeatherDataApiImpl)
                    .returnSuccess = false

                viewModel.dispatch(RefreshWeatherData)

                assertEquals(
                    HomeScreenState(
                        isLoading = true,
                        isLocalityDataLoading = false,
                        selectedLocality = DEFAULT_LOCALITY,
                        weatherData = weatherData,
                        localitiesMap = localitiesMap,
                        uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                assertEquals(Error(message = "Something went wrong"), eventTurbine.awaitItem())

                assertEquals(
                    HomeScreenState(
                        isLoading = true,
                        isLocalityDataLoading = false,
                        selectedLocality = DEFAULT_LOCALITY,
                        weatherData = WeatherData(),
                        localitiesMap = localitiesMap,
                        uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )

                assertEquals(
                    HomeScreenState(
                        isLoading = false,
                        isLocalityDataLoading = false,
                        selectedLocality = DEFAULT_LOCALITY,
                        weatherData = WeatherData(),
                        localitiesMap = localitiesMap,
                        uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    ),
                    stateTurbine.awaitItem(),
                )
            }
        }
}
