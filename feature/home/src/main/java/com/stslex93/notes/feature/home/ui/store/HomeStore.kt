package com.stslex93.notes.feature.home.ui.store

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.stslex93.notes.core.ui.base.store.Store
import com.stslex93.notes.feature.home.ui.model.Note
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action
import com.stslex93.notes.feature.home.ui.store.HomeStore.Event
import com.stslex93.notes.feature.home.ui.store.HomeStore.State
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.coroutines.flow.Flow

interface HomeStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val query: String,
        val selectedNotes: ImmutableSet<Int>,
        val notes: () -> Flow<PagingData<Note>>
    ) : Store.State

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class OnNoteClick(
            val id: Int
        ) : Action

        @Stable
        data class OnNoteLongClick(
            val id: Int
        ) : Action

        @Stable
        data class QueryInput(
            val query: String
        ) : Action

        @Stable
        data object ClearSelection : Action

        @Stable
        data object OnNoteFloatingButtonClick : Action

        @Stable
        data object OnLabelEditClick : Action
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        sealed interface Navigation : Event {

            @Stable
            data class EditNote(
                val noteId: Int,
                val isEdit: Boolean
            ) : Navigation

            @Stable
            data class EditLabel(
                val noteIds: ImmutableSet<Int>
            ) : Navigation
        }
    }
}
