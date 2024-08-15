package com.areeb.weatherunion.logic.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.areeb.weatherunion.core.logger.Logger
import com.areeb.weatherunion.core.viewmodel.BaseViewModel
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

class HomeScreenViewModel(
    private val logger: Logger,
) : BaseViewModel<HomeScreenState, HomeScreenEvent, HomeScreenAction>(
    initialState = HomeScreenState,
    logger = logger,
) {

    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun dispatch(action: HomeScreenAction) {
        logger.debug(TAG, "dispatch $action")
    }

    companion object {
        class Factory @Inject constructor(
            private val logger: Logger,
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return HomeScreenViewModel(
                    logger = logger,
                ) as T
            }
        }
    }
}
