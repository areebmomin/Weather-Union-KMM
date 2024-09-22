package com.areeb.weatherunion.logic.location_data_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepository
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepository
import com.areeb.weatherunion.data.utils.DEFAULT_LOCALITY
import com.areeb.weatherunion.logic.home_screen.usecases.WeatherDataConverterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class LocationDataScreenViewModel @Inject constructor(
    private val coreLogger: CoreLogger,
    private val localitiesDataRepository: LocalitiesDataRepository,
    private val weatherDataRepository: WeatherDataRepository,
    private val weatherDataConverterUseCase: WeatherDataConverterUseCase,
) : BaseViewModel<LocationDataScreenState, LocationDataScreenEvent, LocationDataScreenAction>(
    initialState = LocationDataScreenState(),
    coreLogger = coreLogger,
) {

    init {
        loadLocalityData()
        getLastSelectedIdWeatherData()
    }

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun dispatch(action: LocationDataScreenAction) {
        coreLogger.debug(TAG, "dispatch $action")
        when (action) {
            is OnLocalitySelected -> {
                fetchWeatherData(
                    latitude = action.locality.latitude.toFloat(),
                    longitude = action.locality.longitude.toFloat(),
                )
            }
        }
    }

    private fun loadLocalityData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            updateState(latestState.copy(isLocalityDataLoading = true))
            var localitiesMap = localitiesDataRepository.getLocalitiesMap()

            if (localitiesMap.isEmpty()) {
                localitiesDataRepository.insertLocalityDataInDB()
                localitiesMap = localitiesDataRepository.getLocalitiesMap()
            }

            updateState(
                latestState.copy(
                    localitiesMap = localitiesMap,
                    isLocalityDataLoading = false,
                )
            )
        }
    }

    private fun getLastSelectedIdWeatherData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            var lastSelectedLocality = localitiesDataRepository.getLastSelectedLocality()
            if (lastSelectedLocality.localityId.isEmpty()) lastSelectedLocality = DEFAULT_LOCALITY

            fetchWeatherData(
                latitude = lastSelectedLocality.latitude.toFloat(),
                longitude = lastSelectedLocality.longitude.toFloat(),
            )
        }
    }

    private fun fetchWeatherData(latitude: Float, longitude: Float) {
        viewModelScope.launch(context = Dispatchers.IO) {
            updateState(latestState.copy(isLoading = true))
            val response = weatherDataRepository.getWeatherData(
                latitude = latitude,
                longitude = longitude,
            )
            when (response) {
                is ApiResponse.Success -> {
                    val weatherData = weatherDataConverterUseCase.invoke(response.body)
                    updateState(latestState.copy(weatherData = weatherData))
                }

                is ApiResponse.Error.HttpError -> {
                    triggerEvent(Error(message = response.errorMessage ?: "Something went wrong"))
                }

                is ApiResponse.Error.SerializationError -> {
                    triggerEvent(Error(message = response.errorMessage ?: "Something went wrong"))
                }

                is ApiResponse.Error.GenericError -> {
                    triggerEvent(Error(message = response.errorMessage ?: "Something went wrong"))
                }
            }
            updateState(latestState.copy(isLoading = false))
        }
    }

    companion object {
        class Factory @Inject constructor(
            private val coreLogger: CoreLogger,
            private val localitiesDataRepository: LocalitiesDataRepository,
            private val weatherDataRepository: WeatherDataRepository,
            private val weatherDataConverterUseCase: WeatherDataConverterUseCase,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return LocationDataScreenViewModel(
                    coreLogger = coreLogger,
                    localitiesDataRepository = localitiesDataRepository,
                    weatherDataRepository = weatherDataRepository,
                    weatherDataConverterUseCase = weatherDataConverterUseCase,
                ) as T
            }
        }
    }
}
