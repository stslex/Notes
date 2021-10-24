package com.stslex93.notes.di.module

import com.stslex93.notes.ui.core.NoteListMapper
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @Binds
    fun bindsNoteListMapper(mapper: NoteListMapper.Base): NoteListMapper
}