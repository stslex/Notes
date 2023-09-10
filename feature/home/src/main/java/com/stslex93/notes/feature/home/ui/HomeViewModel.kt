package com.stslex93.notes.feature.home.ui

import com.stslex93.notes.core.ui.base.BaseViewModel
import com.stslex93.notes.feature.home.navigation.HomeRouter
import com.stslex93.notes.feature.home.ui.store.HomeStore
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action
import com.stslex93.notes.feature.home.ui.store.HomeStore.Event
import com.stslex93.notes.feature.home.ui.store.HomeStore.Event.Navigation
import com.stslex93.notes.feature.home.ui.store.HomeStore.State
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    store: HomeStore,
    private val router: HomeRouter,
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Navigation) {
        when (event) {
            is Navigation.EditNote -> router.navToEditNote(event.noteId)
            is Navigation.CreateNote -> router.navToCreateNote()
            is Navigation.EditLabel -> router.navToEditLabel(event.noteIds)
        }
    }
}