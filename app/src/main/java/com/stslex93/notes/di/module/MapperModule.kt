package com.stslex93.notes.di.module

import com.stslex93.notes.data.entity.NoteEntityDataMapper
import com.stslex93.notes.data.model.NoteDataEntityMapper
import com.stslex93.notes.data.model.NoteDataUIMapper
import com.stslex93.notes.data.model.NoteListDataUIMapper
import com.stslex93.notes.ui.core.NoteListMapper
import com.stslex93.notes.ui.model.NoteUIDataMapper
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

    @Binds
    fun bindsNoteDataEntityMapper(mapper: NoteDataEntityMapper.Base): NoteDataEntityMapper

    @Binds
    fun bindsNoteDataUIMapper(mapper: NoteDataUIMapper.Base): NoteDataUIMapper

    @Binds
    fun bindsNoteUIDataMapper(mapper: NoteUIDataMapper.Base): NoteUIDataMapper
}