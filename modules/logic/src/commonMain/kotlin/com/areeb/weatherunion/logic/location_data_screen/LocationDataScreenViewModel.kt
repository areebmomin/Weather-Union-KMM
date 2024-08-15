package com.areeb.weatherunion.logic.location_data_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.Logger
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class LocationDataScreenViewModel @Inject constructor(
    private val logger: Logger,
) : BaseViewModel<LocationDataScreenState, LocationDataScreenEvent, LocationDataScreenAction>(
    initialState = LocationDataScreenState,
    logger = logger,
) {

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun dispatch(action: LocationDataScreenAction) {
        logger.debug(TAG, "dispatch $action")
    }

    companion object {
        class Factory @Inject constructor(
            private val logger: Logger,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return LocationDataScreenViewModel(
                    logger = logger,
                ) as T
            }
        }
    }
}
