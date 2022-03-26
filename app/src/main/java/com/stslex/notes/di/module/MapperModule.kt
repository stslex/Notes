package com.stslex.notes.di.module

import com.stslex.notes.data.entity.NoteEntityDataMapper
import com.stslex.notes.data.model.NoteDataEntityMapper
import com.stslex.notes.data.model.NoteDataUIMapper
import com.stslex.notes.data.model.NoteListDataUIMapper
import com.stslex.notes.ui.core.NoteListMapper
import com.stslex.notes.ui.model.NoteUIDataMapper
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