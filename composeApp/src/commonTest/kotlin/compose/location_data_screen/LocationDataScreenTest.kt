package compose.location_data_screen

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.runComposeUiTest
import com.areeb.weatherunion.core.coroutines.AppCoroutineDispatchers
import com.areeb.weatherunion.data.fakes.api.FakeLatLonWeatherDataApiImpl
import com.areeb.weatherunion.data.fakes.api.FakeLocalityWeatherDataApiImpl
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepositoryImpl
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepositoryImpl
import com.areeb.weatherunion.logic.home_screen.usecases.WeatherDataConverterUseCase
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import core.logger.TestLogger
import data.dao.FakeLocalityDaoImpl
import data.preference.FakeWeatherUnionPreferenceImpl
import location_data_screen.LocationDataScreen
import kotlin.test.Test
import kotlin.test.assertTrue

class LocationDataScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testDisplaysWeatherData() = runComposeUiTest {
        val viewModel = LocationDataScreenViewModel(
            coreLogger = TestLogger(),
            coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
            localitiesDataRepository = LocalitiesDataRepositoryImpl(
                weatherUnionPreferences = FakeWeatherUnionPreferenceImpl(),
                localityDao = FakeLocalityDaoImpl(),
            ),
            weatherDataRepository = WeatherDataRepositoryImpl(
                latLonWeatherDataApi = FakeLatLonWeatherDataApiImpl(),
                localityWeatherDataApi = FakeLocalityWeatherDataApiImpl(),
            ),
            weatherDataConverterUseCase = WeatherDataConverterUseCase(),
        )

        setContent {
            LocationDataScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onBackPressed = {},
            )
        }

        waitForIdle()
        onRoot().printToLog("LocationDataScreenTest")

        onNodeWithText("Bandra West").assertIsDisplayed()

        onNodeWithText("20").assertIsDisplayed()
        onNodeWithText("℃").assertIsDisplayed()

        onNodeWithText("Humidity").assertIsDisplayed()
        onNodeWithText("12%").assertIsDisplayed()

        onNodeWithText("Wind Speed").assertIsDisplayed()
        onNodeWithText("5.4 km/h").assertIsDisplayed()

        onNodeWithText("Wind Direction").assertIsDisplayed()
        onNodeWithText("NE").assertIsDisplayed()

        onNodeWithText("Rain Intensity").assertIsDisplayed()
        onNodeWithText("12.4 mm/min").assertIsDisplayed()

        onNodeWithText("Total Rainfall").assertIsDisplayed()
        onNodeWithText("5.5 mm").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLocationDataScreenButtonClicks() = runComposeUiTest {
        val viewModel = LocationDataScreenViewModel(
            coreLogger = TestLogger(),
            coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
            localitiesDataRepository = LocalitiesDataRepositoryImpl(
                weatherUnionPreferences = FakeWeatherUnionPreferenceImpl(),
                localityDao = FakeLocalityDaoImpl(),
            ),
            weatherDataRepository = WeatherDataRepositoryImpl(
                latLonWeatherDataApi = FakeLatLonWeatherDataApiImpl(),
                localityWeatherDataApi = FakeLocalityWeatherDataApiImpl(),
            ),
            weatherDataConverterUseCase = WeatherDataConverterUseCase(),
        )
        var backButtonClicked = false

        setContent {
            LocationDataScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onBackPressed = {
                    backButtonClicked = true
                },
            )
        }

        onNodeWithContentDescription("Navigate to Home Page").performClick()
        assertTrue(backButtonClicked)
    }
}
