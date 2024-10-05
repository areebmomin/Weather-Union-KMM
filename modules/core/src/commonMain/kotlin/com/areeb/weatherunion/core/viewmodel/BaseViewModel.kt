package com.areeb.weatherunion.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.weatherunion.core.logger.CoreLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.areeb.weatherunion.core.viewmodel.IBaseViewModel as WeatherUnionViewModel

abstract class BaseViewModel<State : Any, Event : Any, Action : Any>(
    initialState: State,
    private val coreLogger: CoreLogger,
) : WeatherUnionViewModel<State, Event, Action>, ViewModel() {

    protected abstract val TAG: String

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<State>
        get() = _state.onStart {
            onInit()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = latestState,
        )

    override val latestState: State
        get() = _state.value

    private val _event = MutableSharedFlow<Event?>()
    override val event: Flow<Event>
        get() = _event.filterNotNull()

    protected fun updateState(state: State) {
        _state.update {
            state
        }

        coreLogger.debug(TAG, "State updated: $state")
    }

    protected suspend fun triggerEvent(event: Event) {
        _event.emit(event)

        coreLogger.debug(TAG, "Event triggered: $event")
    }
}
