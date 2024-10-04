package com.areeb.weatherunion.testing.logic.di

import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.data.repository.api_credential.ApiCredentialRepositoryImpl
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepository
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepositoryImpl
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepository
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepositoryImpl
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import com.areeb.weatherunion.logic.home_screen.usecases.WeatherDataConverterUseCase
import com.areeb.weatherunion.logic.home_screen.viewmodel.HomeScreenViewModel
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import com.areeb.weatherunion.testing.core.logger.TestLogger
import com.areeb.weatherunion.testing.data.api.lat_lon_weather_data.FakeLatLonWeatherDataApiImpl
import com.areeb.weatherunion.testing.data.api.locality_weather_data.FakeLocalityWeatherDataApiImpl
import com.areeb.weatherunion.testing.data.dao.FakeApiKeyDaoImpl
import com.areeb.weatherunion.testing.data.dao.FakeLocalityDaoImpl
import com.areeb.weatherunion.testing.data.preference.FakeWeatherUnionPreferenceImpl
import me.tatarka.inject.internal.LazyMap
import me.tatarka.inject.internal.ScopedComponent

actual fun createTestApplicationComponent(): TestApplicationComponent =
    TestApplicationComponentImpl()

class TestApplicationComponentImpl : TestApplicationComponent(), ScopedComponent {
    override val _scoped: LazyMap = LazyMap()

    private val coreLogger: CoreLogger
        get() = _scoped.get("com.areeb.weatherunion.core.logger.CoreLogger") {
            TestLogger().binds
        }

    private val localitiesDataRepository: LocalitiesDataRepository
        get() = LocalitiesDataRepositoryImpl(
            weatherUnionPreferences =
            _scoped.get("com.areeb.weatherunion.`data`.preference.WeatherUnionPreferences") {
                FakeWeatherUnionPreferenceImpl().binds
            },
            localityDao = FakeLocalityDaoImpl().binds
        ).binds

    private val weatherDataRepository: WeatherDataRepository
        get() = WeatherDataRepositoryImpl(
            latLonWeatherDataApi = latLonWeatherDataApi(
                impl = FakeLatLonWeatherDataApiImpl()
            ),
            localityWeatherDataApi = localityWeatherDataApi(
                impl = FakeLocalityWeatherDataApiImpl()
            )
        ).binds

    override val apiCredentialScreenViewModelFactory: ApiCredentialScreenViewModel.Companion.Factory
        get() = ApiCredentialScreenViewModel.Companion.Factory(
            apiCredentialRepository = ApiCredentialRepositoryImpl(
                apiKeyDao = FakeApiKeyDaoImpl().binds
            ).binds,
            coreLogger = coreLogger
        )

    override val homeScreenViewModelFactory: HomeScreenViewModel.Companion.Factory
        get() = HomeScreenViewModel.Companion.Factory(
            coreLogger = coreLogger,
            localitiesDataRepository = localitiesDataRepository,
            weatherDataRepository = weatherDataRepository,
            weatherDataConverterUseCase = WeatherDataConverterUseCase()
        )

    override val locationDataScreenViewModelFactory: LocationDataScreenViewModel.Companion.Factory
        get() = LocationDataScreenViewModel.Companion.Factory(
            coreLogger = coreLogger,
            localitiesDataRepository = localitiesDataRepository,
            weatherDataRepository = weatherDataRepository,
            weatherDataConverterUseCase = WeatherDataConverterUseCase()
        )
}
