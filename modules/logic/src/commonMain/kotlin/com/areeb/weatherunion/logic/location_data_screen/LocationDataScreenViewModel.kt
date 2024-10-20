package com.areeb.weatherunion.logic.location_data_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepository
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepository
import com.areeb.weatherunion.logic.home_screen.usecases.WeatherDataConverterUseCase
import com.areeb.weatherunion.logic.models.WeatherData
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class LocationDataScreenViewModel @Inject constructor(
    private val coreLogger: CoreLogger,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val localitiesDataRepository: LocalitiesDataRepository,
    private val weatherDataRepository: WeatherDataRepository,
    private val weatherDataConverterUseCase: WeatherDataConverterUseCase,
) : BaseViewModel<LocationDataScreenState, LocationDataScreenEvent, LocationDataScreenAction>(
    initialState = LocationDataScreenState(),
    coreLogger = coreLogger,
) {

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun onInit() {
        loadLocalityData()
        getLastSelectedIdWeatherData()
    }

    override fun dispatch(action: LocationDataScreenAction) {
        coreLogger.debug(TAG, "dispatch $action")
        when (action) {
            is OnLocalitySelected -> {
                updateLastSelectedLocalityAndFetchWeatherData(locality = action.locality)
            }
        }
    }

    private fun loadLocalityData() {
        if (latestState.localityList.isNotEmpty()) return

        viewModelScope.launch(context = coroutineDispatchers.io) {
            updateState(latestState.copy(isLocalityDataLoading = true))
            var localityList = localitiesDataRepository.getLocalityList()

            if (localityList.isEmpty()) {
                localitiesDataRepository.insertLocalityDataInDB()
                localityList = localitiesDataRepository.getLocalityList()
            }

            updateState(
                latestState.copy(
                    localityList = localityList,
                    isLocalityDataLoading = false,
                )
            )
        }
    }

    private fun getLastSelectedIdWeatherData() {
        viewModelScope.launch(context = coroutineDispatchers.io) {
            val lastSelectedLocality = localitiesDataRepository.getLastSelectedLocality()
            if (lastSelectedLocality.localityId.isNotEmpty()) {
                updateState(latestState.copy(selectedLocality = lastSelectedLocality))
            }

            fetchWeatherData(
                latitude = latestState.selectedLocality.latitude.toFloat(),
                longitude = latestState.selectedLocality.longitude.toFloat(),
            )
        }
    }

    private fun updateLastSelectedLocalityAndFetchWeatherData(locality: LocalityData) {
        viewModelScope.launch(coroutineDispatchers.io) {
            updateState(latestState.copy(selectedLocality = locality))
            localitiesDataRepository.setLastSelectedLocality(locality = locality)

            fetchWeatherData(
                latitude = locality.latitude.toFloat(),
                longitude = locality.longitude.toFloat(),
            )
        }
    }

    private fun fetchWeatherData(latitude: Float, longitude: Float) {
        viewModelScope.launch(context = coroutineDispatchers.io) {
            updateState(latestState.copy(isLoading = true))
            val response = weatherDataRepository.getWeatherData(
                latitude = latitude,
                longitude = longitude,
            )
            when (response) {
                is ApiResponse.Success -> {
                    val weatherData = weatherDataConverterUseCase.invoke(response.body)
                    updateState(latestState.copy(weatherData = weatherData, isLoading = false))
                }

                is ApiResponse.Error.HttpError -> {
                    updateState(latestState.copy(weatherData = WeatherData(), isLoading = false))
                    triggerEvent(Error(message = response.errorMessage ?: "Something went wrong"))
                }

                is ApiResponse.Error.SerializationError -> {
                    updateState(latestState.copy(weatherData = WeatherData(), isLoading = false))
                    triggerEvent(Error(message = response.errorMessage ?: "Something went wrong"))
                }

                is ApiResponse.Error.GenericError -> {
                    updateState(latestState.copy(weatherData = WeatherData(), isLoading = false))
                    triggerEvent(Error(message = response.errorMessage ?: "Something went wrong"))
                }
            }
        }
    }

    companion object {
        class Factory @Inject constructor(
            private val coreLogger: CoreLogger,
            private val coroutineDispatchers: CoroutineDispatchers,
            private val localitiesDataRepository: LocalitiesDataRepository,
            private val weatherDataRepository: WeatherDataRepository,
            private val weatherDataConverterUseCase: WeatherDataConverterUseCase,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return LocationDataScreenViewModel(
                    coreLogger = coreLogger,
                    coroutineDispatchers = coroutineDispatchers,
                    localitiesDataRepository = localitiesDataRepository,
                    weatherDataRepository = weatherDataRepository,
                    weatherDataConverterUseCase = weatherDataConverterUseCase,
                ) as T
            }
        }
    }
}
