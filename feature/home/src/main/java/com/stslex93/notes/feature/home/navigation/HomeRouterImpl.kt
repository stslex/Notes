package com.stslex93.notes.feature.home.navigation

import com.stslex93.notes.core.navigation.model.NavigationScreen
import com.stslex93.notes.core.ui.di.Navigator
import javax.inject.Inject

class HomeRouterImpl @Inject constructor(
    private val navigator: Navigator
) : HomeRouter {

    override fun navToEditNote(id: Int) {
        navigator.invoke(
            NavigationScreen.EditNoteScreen(
                noteId = id,
                isEdit = true
            )
        )
    }

    override fun navToCreateNote() {
        navigator.invoke(
            NavigationScreen.EditNoteScreen(
                noteId = -1,
                isEdit = false
            )
        )
    }

    override fun navToEditLabel(noteIds: Set<Int>) {
        navigator.invoke(NavigationScreen.EditLabelScreen(noteIds))
    }
}