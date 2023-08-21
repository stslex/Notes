package com.stslex93.notes.ui.edit.store

import com.stslex93.notes.core.ui.base.store.BaseStoreImpl
import com.stslex93.notes.domain.interactor.interf.NoteEditInteractor
import com.stslex93.notes.ui.edit.model.Note
import com.stslex93.notes.ui.edit.model.toDomain
import com.stslex93.notes.ui.edit.model.toPresentation
import com.stslex93.notes.ui.edit.store.EditStore.Action
import com.stslex93.notes.ui.edit.store.EditStore.Event
import com.stslex93.notes.ui.edit.store.EditStore.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditStoreImpl @Inject constructor(
    private val interactor: NoteEditInteractor
) : EditStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State
        get() = State(
            note = Note(
                uuid = 0,
                title = "",
                content = "",
                timestamp = System.currentTimeMillis()
            )
        )

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
            interactor.insert(note.toDomain())
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
        if (action.isEdit.not()) return
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