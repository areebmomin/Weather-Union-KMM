package com.areeb.weatherunion.core.viewmodel

import androidx.lifecycle.ViewModel
import com.areeb.weatherunion.core.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import com.areeb.weatherunion.core.viewmodel.IBaseViewModel as WeatherUnionViewModel

abstract class BaseViewModel<State : Any, Event : Any, Action : Any>(
    initialState: State,
    private val logger: Logger,
) : WeatherUnionViewModel<State, Event, Action>, ViewModel() {

    protected abstract val TAG: String

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<State>
        get() = _state

    override val latestState: State
        get() = _state.value

    private val _event = MutableSharedFlow<Event?>()
    override val event: Flow<Event>
        get() = _event.filterNotNull()

    protected fun updateState(state: State) {
        _state.update {
            state
        }

        logger.debug(TAG, "State updated: $state")
    }

    protected suspend fun triggerEvent(event: Event) {
        _event.emit(event)

        logger.debug(TAG, "Event triggered: $event")
    }
}
