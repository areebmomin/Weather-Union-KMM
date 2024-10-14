package compose.home_screen

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
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
import com.areeb.weatherunion.logic.home_screen.viewmodel.HomeScreenViewModel
import core.logger.TestLogger
import data.dao.FakeLocalityDaoImpl
import data.preference.FakeWeatherUnionPreferenceImpl
import home_screen.HomeScreen
import kotlin.test.Test
import kotlin.test.assertTrue

class HomeScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun homeScreenDisplaysUiMenuElements() = runComposeUiTest {
        val viewModel = HomeScreenViewModel(
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
            HomeScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onLocationDataMenuClicked = {},
                onEnterApiKeyMenuClicked = {},
                onInfoMenuClicked = {},
            )
        }

        onNodeWithText("Refresh").assertDoesNotExist()
        onNodeWithText("Location Data").assertDoesNotExist()
        onNodeWithText("Enter API Key").assertDoesNotExist()
        onNodeWithText("Info").assertDoesNotExist()

        onNodeWithContentDescription("Menu").performClick()

        onNodeWithText("Refresh").assertIsDisplayed()
        onNodeWithText("Location Data").assertIsDisplayed()
        onNodeWithText("Enter API Key").assertIsDisplayed()
        onNodeWithText("Info").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun homeScreenMenuClickTriggersCallbacks() = runComposeUiTest {
        val viewModel = HomeScreenViewModel(
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
        var locationDataClicked = false
        var enterApiKeyClicked = false
        var infoClicked = false

        setContent {
            HomeScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onLocationDataMenuClicked = { locationDataClicked = true },
                onEnterApiKeyMenuClicked = { enterApiKeyClicked = true },
                onInfoMenuClicked = { infoClicked = true },
            )
        }

        onNodeWithContentDescription("Menu").performClick()
        onNodeWithText("Location Data").performClick()
        onNodeWithContentDescription("Menu").performClick()
        onNodeWithText("Enter API Key").performClick()
        onNodeWithContentDescription("Menu").performClick()
        onNodeWithText("Info").performClick()

        assertTrue(locationDataClicked)
        assertTrue(enterApiKeyClicked)
        assertTrue(infoClicked)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHomeScreenDisplaysWeatherData() = runComposeUiTest {
        val viewModel = HomeScreenViewModel(
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
            HomeScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onLocationDataMenuClicked = {},
                onEnterApiKeyMenuClicked = {},
                onInfoMenuClicked = {},
            )
        }

        waitForIdle()
        onRoot().printToLog("HomeScreenTest")

        onNodeWithText("Bandra West").assertIsDisplayed()

        onNodeWithText("20").assertIsDisplayed()
        onNodeWithText("℃").assertIsDisplayed()

        onNodeWithText("Humidity").assertIsDisplayed()
        onNodeWithText("12").assertIsDisplayed()
        onNodeWithText("%").assertIsDisplayed()

        onNodeWithText("Wind Speed").assertIsDisplayed()
        onNodeWithText("5.4").assertIsDisplayed()
        onNodeWithText(" km/h").assertIsDisplayed()

        onNodeWithText("Wind Direction").assertIsDisplayed()
        onNodeWithText("NE").assertIsDisplayed()

        onNodeWithText("Rain Intensity").assertIsDisplayed()
        onNodeWithText("12.4").assertIsDisplayed()
        onNodeWithText(" mm/min").assertIsDisplayed()

        onNodeWithText("Total Rainfall").assertIsDisplayed()
        onNodeWithText("5.5").assertIsDisplayed()
        onNodeWithText(" mm").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHomeScreenCitiesBottomSheet() = runComposeUiTest {
        val viewModel = HomeScreenViewModel(
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
            HomeScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onLocationDataMenuClicked = {},
                onEnterApiKeyMenuClicked = {},
                onInfoMenuClicked = {},
            )
        }

        onNodeWithTag("city_text_field").performClick()

        onNodeWithText("Select City").assertIsDisplayed()
        onAllNodesWithText("Mumbai")[0].assertIsDisplayed()
        onAllNodesWithText("Bengaluru")[0].assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHomeScreenLocalitiesBottomSheet() = runComposeUiTest {
        val viewModel = HomeScreenViewModel(
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
            HomeScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onLocationDataMenuClicked = {},
                onEnterApiKeyMenuClicked = {},
                onInfoMenuClicked = {},
            )
        }

        onNodeWithTag("locality_text_field").performClick()

        onNodeWithText("Select Area").assertIsDisplayed()
        onNodeWithText("Ghatkopar East, Mumbai").assertIsDisplayed()
        onNodeWithText("Andheri West").assertIsDisplayed()
    }
}
