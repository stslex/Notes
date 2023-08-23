package com.stslex93.notes.feature.edit.ui

import com.stslex93.notes.core.navigation.AppArguments
import com.stslex93.notes.core.navigation.navigator.Navigator
import com.stslex93.notes.core.ui.base.BaseViewModel
import com.stslex93.notes.feature.edit.ui.store.EditStore
import st.slex.csplashscreen.core.navigation.NavigationScreen

class EditNoteViewModel(
    store: EditStore,
    private val navigator: Navigator,
    arguments: AppArguments.NoteEdit
) : BaseViewModel<EditStore.State, EditStore.Event, EditStore.Action>(store) {

    init {
        sendAction(
            EditStore.Action.Init(
                id = arguments.noteId,
                isEdit = arguments.isEdit
            )
        )
    }

    fun popBackStack() {
        navigator(NavigationScreen.PopBackStack)
    }
}