package com.stslex93.notes.core.ui.base.store

import com.stslex93.notes.core.ui.base.store.Store.Action
import com.stslex93.notes.core.ui.base.store.Store.Event
import com.stslex93.notes.core.ui.base.store.Store.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

abstract class BaseStoreImpl<S : State, E : Event, A : Action> :
    Store<S, E, A>,
    StoreImpl<S, E, A> {

    private var _scope: CoroutineScope? = null
    val scope: CoroutineScope
        get() = requireNotNull(_scope) { "Scope should be set" }

    abstract val initialState: S

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)

    override val state: StateFlow<S>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<A>()
    override val action: SharedFlow<A>
        get() = _action.asSharedFlow()

    private val _event = MutableSharedFlow<E>()
    override val event: SharedFlow<E>
        get() = _event

    override fun updateState(update: (S) -> S) {
        _state.update(update)
    }

    override fun sendAction(action: A) {
        scope.launch {
            _action.emit(action)
        }
    }

    override fun sendEvent(event: E) {
        scope.launch {
            _event.emit(event)
        }
    }

    override suspend fun init() {
        _scope = CoroutineScope(coroutineContext)
        scope.launch {
            action.collect(::processAction)
        }
    }
}