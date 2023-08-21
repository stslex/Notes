package com.stslex93.notes.ui.edit

import com.stslex93.notes.core.ui.base.BaseViewModel
import com.stslex93.notes.ui.edit.store.EditStore

class EditNoteViewModel(
    store: EditStore
) : BaseViewModel<EditStore.State, EditStore.Event, EditStore.Action>(store)