package com.stslex93.notes.di.module

import com.stslex93.notes.domain.interactor.impl.NoteDeleteByIdsInteractorImpl
import com.stslex93.notes.domain.interactor.impl.NoteEditInteractorImpl
import com.stslex93.notes.domain.interactor.impl.NoteGetAllWithQueryInteractorImpl
import com.stslex93.notes.domain.interactor.impl.NoteInsertAllInteractorImpl
import com.stslex93.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex93.notes.domain.interactor.interf.NoteEditInteractor
import com.stslex93.notes.domain.interactor.interf.NoteGetAllWithQueryInteractor
import com.stslex93.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex93.notes.ui.edit.store.EditStore
import com.stslex93.notes.ui.edit.store.EditStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindsNoteDeleteByIdsInteractor(interactor: NoteDeleteByIdsInteractorImpl): NoteDeleteByIdsInteractor

    @Binds
    fun bindsNoteEditInteractor(interactor: NoteEditInteractorImpl): NoteEditInteractor

    @Binds
    fun bindsNotesGetAllInteractor(interactor: NoteGetAllWithQueryInteractorImpl): NoteGetAllWithQueryInteractor

    @Binds
    fun bindsNoteInsertAllInteractor(interactor: NoteInsertAllInteractorImpl): NoteInsertAllInteractor

    @Binds
    fun bindsEditStore(store: EditStoreImpl): EditStore
}