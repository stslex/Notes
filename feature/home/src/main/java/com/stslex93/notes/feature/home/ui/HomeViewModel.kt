package com.stslex93.notes.feature.home.ui

import com.stslex93.notes.core.navigation.model.NavigationScreen
import com.stslex93.notes.core.ui.base.BaseViewModel
import com.stslex93.notes.core.ui.di.Navigator
import com.stslex93.notes.feature.home.ui.store.HomeStore
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action
import com.stslex93.notes.feature.home.ui.store.HomeStore.Event
import com.stslex93.notes.feature.home.ui.store.HomeStore.State
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    store: HomeStore,
    private val navigator: Navigator
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Event.Navigation) {
        when (event) {
            is Event.Navigation.EditNote -> navToEditNote(event)
        }
    }

    private fun navToEditNote(event: Event.Navigation.EditNote) {
        navigator.invoke(
            NavigationScreen.EditNoteScreen(
                noteId = event.noteId,
                isEdit = event.isEdit
            )
        )
    }
}