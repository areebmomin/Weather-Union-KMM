package com.areeb.weatherunion.logic.api_credential_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import com.areeb.weatherunion.data.repository.api_credential.ApiCredentialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class ApiCredentialScreenViewModel(
    private val apiCredentialRepository: ApiCredentialRepository,
    private val coreLogger: CoreLogger,
) : BaseViewModel<ApiCredentialScreenState, ApiCredentialScreenEvent, ApiCredentialScreenAction>(
    initialState = ApiCredentialScreenState(),
    coreLogger = coreLogger,
) {

    override val TAG: String
        get() = this::class.simpleName.toString()

    init {
        getApiKeys()
    }

    override fun dispatch(action: ApiCredentialScreenAction) {
        coreLogger.debug(TAG, "dispatch: $action")
        when (action) {
            is UpdateWeatherUnionApiKey -> {
                apiCredentialRepository.updateWeatherUnionApiKey(apiKey = action.apiKey)
            }

            is UpdateMapApiKey -> {
                apiCredentialRepository.updateMapApiKey(apiKey = action.apiKey)
            }
        }
    }

    private fun getApiKeys() {
        viewModelScope.launch(context = Dispatchers.IO) {
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

    companion object {
        class Factory @Inject constructor(
            private val apiCredentialRepository: ApiCredentialRepository,
            private val coreLogger: CoreLogger,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return ApiCredentialScreenViewModel(
                    apiCredentialRepository = apiCredentialRepository,
                    coreLogger = coreLogger,
                ) as T
            }
        }
    }
}
