package com.areeb.weatherunion.logic.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.Logger
import com.areeb.weatherunion.core.network.ApiResponse
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import com.areeb.weatherunion.data.repository.LocalitiesDataRepository
import com.areeb.weatherunion.data.repository.WeatherDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class HomeScreenViewModel(
    private val logger: Logger,
    private val localitiesDataRepository: LocalitiesDataRepository,
    private val weatherDataRepository: WeatherDataRepository,
) : BaseViewModel<HomeScreenState, HomeScreenEvent, HomeScreenAction>(
    initialState = HomeScreenState(),
    logger = logger,
) {

    init {
        loadLocalityData()
        getLastSelectedIdWeatherData()
    }

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun dispatch(action: HomeScreenAction) {
        logger.debug(TAG, "dispatch $action")
        when (action) {
            OnCitySelected -> {

            }

            OnLocalitySelected -> {

            }
        }
    }

    private fun loadLocalityData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            triggerEvent(Loading(isLoading = true)) // TODO: Resolve loader inconsistency issue
            var localities = localitiesDataRepository.getLocalityList()

            if (localities.isEmpty()) {
                localitiesDataRepository.insertLocalityDataInDB()
                localities = localitiesDataRepository.getLocalityList()
            }

            updateState(latestState.copy(localities = localities))
            triggerEvent(Loading(isLoading = false))
        }
    }

    private fun getLastSelectedIdWeatherData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            triggerEvent(Loading(isLoading = true))
            val lastSelectedLocalityId = localitiesDataRepository.getLastSelectedLocalityId()
            val response = weatherDataRepository.getWeatherData(
                locationId = lastSelectedLocalityId,
            )
            when (response) {
                is ApiResponse.Success -> {

                }

                is ApiResponse.Error.HttpError -> {

                }

                is ApiResponse.Error.SerializationError -> {

                }

                is ApiResponse.Error.GenericError -> {

                }
            }
        }
    }

    companion object {
        class Factory @Inject constructor(
            private val logger: Logger,
            private val localitiesDataRepository: LocalitiesDataRepository,
            private val weatherDataRepository: WeatherDataRepository,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return HomeScreenViewModel(
                    logger = logger,
                    localitiesDataRepository = localitiesDataRepository,
                    weatherDataRepository = weatherDataRepository,
                ) as T
            }
        }
    }
}
