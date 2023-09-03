package com.stslex93.notes.feature.edit.ui.store

import com.stslex93.notes.core.ui.base.store.BaseStoreImpl
import com.stslex93.notes.feature.edit.domain.interactor.NoteEditInteractor
import com.stslex93.notes.feature.edit.ui.model.Note
import com.stslex93.notes.feature.edit.ui.model.toDomain
import com.stslex93.notes.feature.edit.ui.model.toPresentation
import com.stslex93.notes.feature.edit.ui.store.EditStore.Action
import com.stslex93.notes.feature.edit.ui.store.EditStore.Event
import com.stslex93.notes.feature.edit.ui.store.EditStore.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditStoreImpl @Inject constructor(
    private val interactor: NoteEditInteractor
) : EditStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        note = Note(
            uuid = 0,
            title = "",
            content = "",
            timestamp = System.currentTimeMillis()
        )
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    override fun processAction(action: Action) {
        when (action) {
            is Action.Init -> initStore(action)
            is Action.InputContent -> onActionInputContent(action)
            is Action.InputTitle -> onActionInputTitle(action)
            is Action.SaveNote -> onActionSaveNote()
        }
    }

    private fun onActionSaveNote() {
        val note = state.value.note
        if (note.title.isBlank() && note.content.isBlank()) return
        scope.launch {
            interactor.insert(
                note.copy(
                    title = note.title.trimEnd(),
                    content = note.content.trimEnd()
                ).toDomain()
            )
        }
    }

    private fun onActionInputTitle(action: Action.InputTitle) {
        updateState { currentState ->
            currentState.copy(
                note = currentState.note.copy(
                    title = action.title
                )
            )
        }
    }

    private fun onActionInputContent(action: Action.InputContent) {
        updateState { currentState ->
            currentState.copy(
                note = currentState.note.copy(
                    content = action.content
                )
            )
        }
    }

    private fun initStore(action: Action.Init) {
        if (action.isEdit || action.id <= -1) return
        interactor.getNote(action.id)
            .onEach { note ->
                updateState { currentState ->
                    currentState.copy(
                        note = note.toPresentation()
                    )
                }
            }
            .launchIn(scope)
    }
}