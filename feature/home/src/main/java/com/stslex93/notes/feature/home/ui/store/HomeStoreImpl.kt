package com.stslex93.notes.feature.home.ui.store

import com.stslex93.notes.core.ui.base.store.BaseStoreImpl
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action
import com.stslex93.notes.feature.home.ui.store.HomeStore.Event
import com.stslex93.notes.feature.home.ui.store.HomeStore.State

class HomeStoreImpl : HomeStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State
        get() = State("")

    override fun processAction(action: Action) {
        when (action) {

            else -> {}
        }
    }
}