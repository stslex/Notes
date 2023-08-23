package com.stslex93.notes.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex93.notes.core.ui.base.store.Store
import com.stslex93.notes.core.ui.base.store.Store.Action
import com.stslex93.notes.core.ui.base.store.Store.Event
import com.stslex93.notes.core.ui.base.store.Store.State
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel<out S : State, out E : Event, in A : Action>(
    private val store: Store<S, E, A>
) : ViewModel() {

    val state: StateFlow<S> = store.state
    val event: SharedFlow<E> = store.event

    init {
        store.init(viewModelScope)
    }

    fun sendAction(action: A) {
        store.processAction(action)
    }
}