package com.areeb.weatherunion.logic.home_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.data.repository.localities_data.LocalitiesDataRepository
import com.areeb.weatherunion.data.repository.weather_data.WeatherDataRepository
import com.areeb.weatherunion.logic.home_screen.usecases.WeatherDataConverterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class HomeScreenViewModel(
    private val coreLogger: CoreLogger,
    private val localitiesDataRepository: LocalitiesDataRepository,
    private val weatherDataRepository: WeatherDataRepository,
    private val weatherDataConverterUseCase: WeatherDataConverterUseCase,
) : BaseViewModel<HomeScreenState, HomeScreenEvent, HomeScreenAction>(
    initialState = HomeScreenState(),
    coreLogger = coreLogger,
) {

    init {
        loadLocalityData()
        getLastSelectedIdWeatherData()
    }

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun dispatch(action: HomeScreenAction) {
        coreLogger.debug(TAG, "dispatch $action")
        when (action) {
            is OnLocalitySelected -> {
                updateLastSelectedLocalityAndFetchWeatherData(locality = action.locality)
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

            val uniqueCitiesFirstLocalityList = localitiesMap.values.mapNotNull { list ->
                list.firstOrNull()
            }

            updateState(
                latestState.copy(
                    localitiesMap = localitiesMap,
                    uniqueCitiesFirstLocalityList = uniqueCitiesFirstLocalityList,
                    isLocalityDataLoading = false
                )
            )
        }
    }

    private fun getLastSelectedIdWeatherData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val lastSelectedLocality = localitiesDataRepository.getLastSelectedLocality()
            if (lastSelectedLocality.localityId.isNotEmpty()) {
                updateState(latestState.copy(selectedLocality = lastSelectedLocality))
            }

            fetchWeatherData(localityId = latestState.selectedLocality.localityId)
        }
    }

    private fun updateLastSelectedLocalityAndFetchWeatherData(locality: LocalityData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(latestState.copy(selectedLocality = locality))
            localitiesDataRepository.setLastSelectedLocality(locality = locality)

            fetchWeatherData(localityId = locality.localityId)
        }
    }

    private fun fetchWeatherData(localityId: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            updateState(latestState.copy(isLoading = true))
            val response = weatherDataRepository.getWeatherData(
                locationId = localityId,
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
                return HomeScreenViewModel(
                    coreLogger = coreLogger,
                    localitiesDataRepository = localitiesDataRepository,
                    weatherDataRepository = weatherDataRepository,
                    weatherDataConverterUseCase = weatherDataConverterUseCase,
                ) as T
            }
        }
    }
}
