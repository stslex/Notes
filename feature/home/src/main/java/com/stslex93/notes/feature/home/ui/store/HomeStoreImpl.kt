package com.stslex93.notes.feature.home.ui.store

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.stslex93.notes.core.ui.Extensions.addItem
import com.stslex93.notes.core.ui.Extensions.removeItem
import com.stslex93.notes.core.ui.base.store.BaseStoreImpl
import com.stslex93.notes.feature.home.domain.interactor.HomeInteractor
import com.stslex93.notes.feature.home.ui.model.Note
import com.stslex93.notes.feature.home.ui.model.toUI
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action
import com.stslex93.notes.feature.home.ui.store.HomeStore.Event
import com.stslex93.notes.feature.home.ui.store.HomeStore.State
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeStoreImpl @Inject constructor(
    private val interactor: HomeInteractor
) : HomeStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState = State(
        query = "",
        selectedNotes = persistentSetOf(),
        notes = ::notes
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val notes: StateFlow<PagingData<Note>>
        get() = state
            .map { currentState ->
                currentState.query
            }
            .distinctUntilChanged()
            .flatMapLatest(interactor::searchNotes)
            .map { pagingData ->
                pagingData.map { note -> note.toUI() }
            }
            .cachedIn(scope)
            .flowOn(Dispatchers.IO)
            .stateIn(
                scope = scope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            )

    override fun processAction(action: Action) {
        when (action) {
            is Action.OnNoteClick -> onNoteClickAction(action)
            is Action.OnNoteLongClick -> onNoteLongClickAction(action)
            is Action.QueryInput -> onQueryInput(action)
            is Action.OnNoteFloatingButtonClick -> onNoteCreateClick()
            is Action.ClearSelection -> clearSelection()
            is Action.OnLabelEditClick -> onLabelEditClick()
        }
    }

    private fun onLabelEditClick() {
        val selectedIds = state.value.selectedNotes
        clearSelection()
        sendEvent(Event.Navigation.EditLabel(selectedIds))
    }

    private fun clearSelection() {
        updateState { currentState ->
            currentState.copy(
                selectedNotes = persistentSetOf(),
                query = ""
            )
        }
    }

    private fun onNoteCreateClick() {
        val items = state.value.selectedNotes
        if (items.isNotEmpty()) {
            scope.launch(Dispatchers.IO) {
                interactor.deleteNotes(items.toList())
                updateState { currentState ->
                    currentState.copy(selectedNotes = persistentSetOf())
                }
            }
        } else {
            sendEvent(Event.Navigation.CreateNote)
        }
    }

    private fun onQueryInput(action: Action.QueryInput) {
        updateState { currentState ->
            currentState.copy(
                query = action.query
            )
        }
    }

    private fun onNoteLongClickAction(action: Action.OnNoteLongClick) {
        updateSelectedNotes(action.id)
    }

    private fun onNoteClickAction(action: Action.OnNoteClick) {
        if (state.value.selectedNotes.isNotEmpty()) {
            updateSelectedNotes(action.id)
        } else {
            sendEvent(
                Event.Navigation.EditNote(action.id)
            )
        }
    }

    private fun updateSelectedNotes(id: Int) {
        updateState { currentState ->
            val selectedItems = if (currentState.selectedNotes.contains(id)) {
                currentState.selectedNotes.removeItem(id)
            } else {
                currentState.selectedNotes.addItem(id)
            }
            currentState.copy(
                selectedNotes = selectedItems,
            )
        }
    }
}