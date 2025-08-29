package com.stslex93.notes.feature.edit.ui

import com.stslex93.notes.core.navigation.v2.NoteEditArgs
import com.stslex93.notes.core.ui.base.BaseViewModel
import com.stslex93.notes.core.ui.di.Navigator
import com.stslex93.notes.feature.edit.ui.store.EditStore
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    store: EditStore,
    private val navigator: Navigator,
) : BaseViewModel<EditStore.State, EditStore.Event, EditStore.Action>(store) {

    fun init(arguments: NoteEditArgs) {
        sendAction(
            EditStore.Action.Init(
                id = arguments.noteId,
                isEdit = arguments.isEdit
            )
        )
    }

    fun popBackStack() {
        navigator.popBackStack()
    }
}