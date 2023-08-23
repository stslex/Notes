package com.stslex93.notes.feature.home.ui.store

import com.stslex93.notes.core.ui.base.store.Store
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action
import com.stslex93.notes.feature.home.ui.store.HomeStore.Event
import com.stslex93.notes.feature.home.ui.store.HomeStore.State

interface HomeStore : Store<State, Event, Action> {

    data class State(
        val t: String
    ) : Store.State

    sealed interface Action : Store.Action

    sealed interface Event : Store.Event {

        sealed interface Navigation : Event {

            data class EditNote(
                val noteId: Int,
                val isEdit: Boolean
            ) : Navigation
        }
    }
}