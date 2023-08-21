package com.stslex93.notes.feature.edit.ui

import com.stslex93.notes.core.ui.base.BaseViewModel
import com.stslex93.notes.feature.edit.ui.store.EditStore

class EditNoteViewModel(
    store: EditStore
) : BaseViewModel<EditStore.State, EditStore.Event, EditStore.Action>(store)