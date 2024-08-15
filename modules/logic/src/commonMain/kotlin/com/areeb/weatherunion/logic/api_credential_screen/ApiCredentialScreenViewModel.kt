package com.areeb.weatherunion.logic.api_credential_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.Logger
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import com.areeb.weatherunion.data.repository.ApiCredentialRepository
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class ApiCredentialScreenViewModel(
    private val apiCredentialRepository: ApiCredentialRepository,
    private val logger: Logger,
) : BaseViewModel<ApiCredentialScreenState, ApiCredentialScreenEvent, ApiCredentialScreenAction>(
    initialState = ApiCredentialScreenState(),
    logger = logger,
) {

    override val TAG: String
        get() = this::class.simpleName.toString()

    init {
        viewModelScope.launch {
            val weatherUnionApiKey = apiCredentialRepository.getWeatherUnionApiKey()
            val mapApiKey = apiCredentialRepository.getMapApiKey()
            updateState(
                latestState.copy(
                    weatherUnionApiKey = weatherUnionApiKey,
                    mapApiKey = mapApiKey,
                )
            )
        }
    }

    override fun dispatch(action: ApiCredentialScreenAction) {
        logger.debug(TAG, "dispatch: $action")
        when (action) {
            is UpdateWeatherUnionApiKey -> {
                apiCredentialRepository.updateWeatherUnionApiKey(apiKey = action.apiKey)
            }

            is UpdateMapApiKey -> {
                apiCredentialRepository.updateMapApiKey(apiKey = action.apiKey)
            }
        }
    }

    companion object {
        class Factory @Inject constructor(
            private val apiCredentialRepository: ApiCredentialRepository,
            private val logger: Logger,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return ApiCredentialScreenViewModel(
                    apiCredentialRepository = apiCredentialRepository,
                    logger = logger,
                ) as T
            }
        }
    }
}
