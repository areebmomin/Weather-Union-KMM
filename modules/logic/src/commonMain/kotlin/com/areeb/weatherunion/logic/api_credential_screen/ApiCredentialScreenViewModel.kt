package com.areeb.weatherunion.logic.api_credential_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import com.areeb.weatherunion.data.repository.api_credential.ApiCredentialRepository
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class ApiCredentialScreenViewModel(
    private val coreLogger: CoreLogger,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val apiCredentialRepository: ApiCredentialRepository,
) : BaseViewModel<ApiCredentialScreenState, ApiCredentialScreenEvent, ApiCredentialScreenAction>(
    initialState = ApiCredentialScreenState(),
    coreLogger = coreLogger,
) {

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun onInit() {
        getApiKeys()
    }

    override fun dispatch(action: ApiCredentialScreenAction) {
        coreLogger.debug(TAG, "dispatch: $action")
        when (action) {
            is UpdateWeatherUnionApiKey -> {
                updateState(latestState.copy(weatherUnionApiKey = action.apiKey))
            }

            SaveWeatherUnionApiKey -> {
                viewModelScope.launch(context = coroutineDispatchers.io) {
                    apiCredentialRepository.updateWeatherUnionApiKey(
                        apiKey = latestState.weatherUnionApiKey,
                    )
                    triggerEvent(ShowSnackBar(message = "Weather Union API Key saved successfully"))
                }
            }
        }
    }

    private fun getApiKeys() {
        viewModelScope.launch(context = coroutineDispatchers.io) {
            val weatherUnionApiKey = apiCredentialRepository.getWeatherUnionApiKey()
            updateState(
                latestState.copy(
                    weatherUnionApiKey = weatherUnionApiKey ?: "",
                )
            )
        }
    }

    companion object {
        class Factory @Inject constructor(
            private val coreLogger: CoreLogger,
            private val coroutineDispatchers: CoroutineDispatchers,
            private val apiCredentialRepository: ApiCredentialRepository,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return ApiCredentialScreenViewModel(
                    coreLogger = coreLogger,
                    coroutineDispatchers = coroutineDispatchers,
                    apiCredentialRepository = apiCredentialRepository,
                ) as T
            }
        }
    }
}
