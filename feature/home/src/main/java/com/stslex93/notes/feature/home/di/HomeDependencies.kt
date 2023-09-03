package com.stslex93.notes.feature.home.di

import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.core.ui.di.Navigator

interface HomeDependencies {

    val noteRepository: NoteRepository

    val navigator: Navigator
}