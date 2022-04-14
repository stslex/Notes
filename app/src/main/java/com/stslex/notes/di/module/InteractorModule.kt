package com.stslex.notes.di.module

import com.stslex.notes.domain.interactor.impl.NoteDeleteByIdsInteractorImpl
import com.stslex.notes.domain.interactor.impl.NoteGetAllWithQueryInteractorImpl
import com.stslex.notes.domain.interactor.impl.NoteGetSingleInteractorImpl
import com.stslex.notes.domain.interactor.impl.NoteInsertSingleInteractorImpl
import com.stslex.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex.notes.domain.interactor.interf.NoteGetAllWithQueryInteractor
import com.stslex.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex.notes.domain.interactor.interf.NoteInsertSingleInteractor
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindsNoteDeleteByIdsInteractor(interactor: NoteDeleteByIdsInteractorImpl): NoteDeleteByIdsInteractor

    @Binds
    fun bindsNoteGetSingleInteractor(interactor: NoteGetSingleInteractorImpl): NoteGetSingleInteractor

    @Binds
    fun bindsNoteInsertSingleInteractor(interactor: NoteInsertSingleInteractorImpl): NoteInsertSingleInteractor

    @Binds
    fun bindsNotesGetAllInteractor(interactor: NoteGetAllWithQueryInteractorImpl): NoteGetAllWithQueryInteractor
}