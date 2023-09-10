package com.stslex93.notes.feature.edit_label.ui

import com.stslex93.notes.core.ui.base.BaseViewModel
import com.stslex93.notes.feature.edit_label.navigation.router.EditLabelRouter
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Action
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Event
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Event.Navigation
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.State
import javax.inject.Inject

class EditLabelViewModel @Inject constructor(
    store: EditLabelStore,
    private val router: EditLabelRouter
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigationEvent(event: Navigation) {
        when (event) {
            Navigation.OnBackPressed -> router.popBackStack()
        }
    }
}