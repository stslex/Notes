package com.stslex93.notes.core.ui.base.store

import com.stslex93.notes.core.ui.base.store.Store.Action
import com.stslex93.notes.core.ui.base.store.Store.Event
import com.stslex93.notes.core.ui.base.store.Store.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Store<out S : State, out E : Event, in A : Action> {

    val state: StateFlow<S>
    val event: SharedFlow<E>

    fun processAction(action: A)

    fun init(scope: CoroutineScope)

    interface State
    interface Event
    interface Action
}

