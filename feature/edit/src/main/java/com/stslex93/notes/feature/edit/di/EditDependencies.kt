package com.stslex93.notes.feature.edit.di

import com.stslex93.notes.core.label.repository.LabelRepository
import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.core.ui.di.Navigator

interface EditDependencies {

    val noteRepository: NoteRepository

    val labelRepository: LabelRepository

    val navigator: Navigator
}