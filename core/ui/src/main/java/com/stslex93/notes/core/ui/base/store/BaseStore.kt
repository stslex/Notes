package com.stslex93.notes.core.ui.base.store

import com.stslex93.notes.core.ui.base.store.Store.Action
import com.stslex93.notes.core.ui.base.store.Store.Event
import com.stslex93.notes.core.ui.base.store.Store.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseStoreImpl<S : State, E : Event, A : Action> :
    Store<S, E, A>,
    StoreImpl<S, E, A> {

    private var _scope: CoroutineScope? = null
    val scope: CoroutineScope
        get() = requireNotNull(_scope)

    @Suppress("LeakingThis")
    override val state: MutableStateFlow<S> = MutableStateFlow(initialState)
    override val event: MutableSharedFlow<E> = MutableSharedFlow()

    override fun updateState(update: (S) -> S) {
        state.update(update)
    }

    override fun sendEvent(event: E) {
        scope.launch {
            this@BaseStoreImpl.event.emit(event)
        }
    }

    override fun init(scope: CoroutineScope) {
        _scope = scope
    }
}