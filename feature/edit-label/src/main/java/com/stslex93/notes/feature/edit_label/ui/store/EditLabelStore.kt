package com.stslex93.notes.feature.edit_label.ui.store

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.stslex93.notes.core.ui.base.store.Store
import com.stslex93.notes.feature.edit_label.ui.model.Label
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Action
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Event
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.State
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.coroutines.flow.StateFlow

interface EditLabelStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val notesIds: ImmutableSet<Int>,
        val query: String,
        val labels: () -> StateFlow<PagingData<Label>>
    ) : Store.State

    @Stable
    sealed interface Event : Store.Event {

        data object SnackbarError : Event

        sealed interface Navigation : Event {

            data object OnBackPressed : Navigation
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class Init(
            val noteIds: List<Int>
        ) : Action

        @Stable
        data class QueryInput(
            val text: String
        ) : Action

        @Stable
        data object AddLabelClicked : Action

        @Stable
        data object BackPressed : Action

        @Stable
        data class OnLabelSelected(
            val labelUuid: String
        ) : Action
    }
}