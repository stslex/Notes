package com.stslex.notes.di.module

import com.stslex.notes.domain.interactor.impl.*
import com.stslex.notes.domain.interactor.interf.*
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindsNoteDeleteByIdsInteractor(interactor: NoteDeleteByIdsInteractorImpl): NoteDeleteByIdsInteractor

    @Binds
    fun bindsNoteGetSingleInteractor(interactor: NoteGetSingleInteractorImpl): NoteGetSingleInteractor

    @Binds
    fun bindsNoteInsertAllInteractor(interactor: NoteInsertAllInteractorImpl): NoteInsertAllInteractor

    @Binds
    fun bindsNoteInsertSingleInteractor(interactor: NoteInsertSingleInteractorImpl): NoteInsertSingleInteractor

    @Binds
    fun bindsNotesGetAllInteractor(interactor: NotesGetAllInteractorImpl): NoteGetAllInteractor

    @Binds
    fun bindsNoteUpdateSingleInteractor(interactor: NoteUpdateSingleInteractorImpl): NoteUpdateSingleInteractor
}