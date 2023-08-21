package com.stslex93.notes.core.ui.base.store

import com.stslex93.notes.core.ui.base.store.Store.Action
import com.stslex93.notes.core.ui.base.store.Store.Event
import com.stslex93.notes.core.ui.base.store.Store.State
import kotlinx.coroutines.flow.SharedFlow

interface StoreImpl<S : State, in E : Event, A : Action> {

    val action: SharedFlow<A>

    fun sendEvent(event: E)

    fun updateState(update: (S) -> S)

    fun processAction(action: A)
}