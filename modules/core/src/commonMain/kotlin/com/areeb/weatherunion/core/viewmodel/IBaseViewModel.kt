package com.areeb.weatherunion.core.viewmodel

import kotlinx.coroutines.flow.Flow

/**
 *  [State] :- State for UI
 *
 *  [Event] :- One time Event for UI (Toast, SnackBar, etc.)
 *
 *  [Action] :- Action taken by user or external factor in UI
 */
interface IBaseViewModel<State : Any, Event : Any, Action : Any> {
    val state: Flow<State>
    val latestState: State
    val event: Flow<Event>

    fun onInit()

    fun dispatch(action: Action)
}
