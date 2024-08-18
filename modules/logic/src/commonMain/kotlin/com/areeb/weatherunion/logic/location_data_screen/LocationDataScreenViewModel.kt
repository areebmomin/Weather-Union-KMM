package com.areeb.weatherunion.logic.location_data_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.CoreLogger
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class LocationDataScreenViewModel @Inject constructor(
    private val coreLogger: CoreLogger,
) : BaseViewModel<LocationDataScreenState, LocationDataScreenEvent, LocationDataScreenAction>(
    initialState = LocationDataScreenState,
    coreLogger = coreLogger,
) {

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun dispatch(action: LocationDataScreenAction) {
        coreLogger.debug(TAG, "dispatch $action")
    }

    companion object {
        class Factory @Inject constructor(
            private val coreLogger: CoreLogger,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return LocationDataScreenViewModel(
                    coreLogger = coreLogger,
                ) as T
            }
        }
    }
}
