package compose.home_screen

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
import kotlin.test.Test

class HomeScreenTest {

    @Test
    fun testHomeScreenDisplaysWeatherData() {
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
    }
}
