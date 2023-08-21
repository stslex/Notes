package com.stslex93.notes.feature.edit.di

import com.stslex93.notes.feature.edit.domain.interactor.NoteEditInteractor
import com.stslex93.notes.feature.edit.domain.interactor.NoteEditInteractorImpl
import com.stslex93.notes.feature.edit.ui.EditNoteViewModel
import com.stslex93.notes.feature.edit.ui.store.EditStore
import com.stslex93.notes.feature.edit.ui.store.EditStoreImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val featureEditModule = module {
    factoryOf(::NoteEditInteractorImpl) { bind<NoteEditInteractor>() }
    viewModelOf(::EditNoteViewModel)
    factoryOf(::EditStoreImpl) { bind<EditStore>() }
}