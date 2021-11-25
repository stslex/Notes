package com.stslex93.notes.di.module

import com.stslex93.notes.data.entity.NoteEntityDataMapper
import com.stslex93.notes.data.model.NoteListDataUIMapper
import com.stslex93.notes.ui.core.NoteListMapper
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @Binds
    fun bindsNoteListMapper(mapper: NoteListMapper.Base): NoteListMapper

    @Binds
    fun bindsNoteEntityDataMapper(mapper: NoteEntityDataMapper.Base): NoteEntityDataMapper

    @Binds
    fun bindsNoteListDataUIMapper(mapper: NoteListDataUIMapper.Base): NoteListDataUIMapper
}