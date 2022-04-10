package com.stslex.notes.di.module

import com.stslex.notes.data.mapper.NoteDataEntityMapper
import com.stslex.notes.data.mapper.NoteEntityDataMapper
import com.stslex.notes.data.mapper.NoteListDataEntityMapper
import com.stslex.notes.data.mapper.NoteListEntityDataMapper
import com.stslex.notes.domain.mappers.*
import com.stslex.notes.ui.mapper.*
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    /*Entity Data*/
    @Binds
    fun bindsNoteEntityDataMapper(mapper: NoteEntityDataMapper.Base): NoteEntityDataMapper

    @Binds
    fun bindsNoteListEntityDataMapper(mapper: NoteListEntityDataMapper.Base): NoteListEntityDataMapper

    /*Data Entity*/
    @Binds
    fun bindsNoteDataEntityMapper(mapper: NoteDataEntityMapper.Base): NoteDataEntityMapper

    @Binds
    fun bindsNoteListDataEntityMapper(mapper: NoteListDataEntityMapper.Base): NoteListDataEntityMapper

    /*Data Domain*/
    @Binds
    fun bindsNoteDataDomainPrimaryMapper(mapper: NoteDataDomainPrimaryMapper.Base): NoteDataDomainPrimaryMapper

    @Binds
    fun bindsNoteDataDomainMapper(mapper: NoteDataDomainMapper.Base): NoteDataDomainMapper

    @Binds
    fun bindsNoteListDataDomainMapper(mapper: NoteListDataDomainMapper.Base): NoteListDataDomainMapper

    /*Domain Data*/
    @Binds
    fun bindsNoteDomainDataMapper(mapper: NoteDomainDataMapper.Base): NoteDomainDataMapper

    @Binds
    fun bindsNoteListDomainDataMapper(mapper: NoteListDomainDataMapper.Base): NoteListDomainDataMapper

    /*Domain UI*/
    @Binds
    fun bindsNoteDomainUIPrimaryMapper(mapper: NoteDomainUIPrimaryMapper.Base): NoteDomainUIPrimaryMapper

    @Binds
    fun bindsNoteDomainUIMapper(mapper: NoteDomainUIMapper.Base): NoteDomainUIMapper

    @Binds
    fun bindsNoteListDomainUIMapper(mapper: NoteListDomainUIMapper.Base): NoteListDomainUIMapper

    /*UI Domain*/
    @Binds
    fun bindsNoteUIDomainMapper(mapper: NoteUIDomainMapper.Base): NoteUIDomainMapper

    @Binds
    fun bindsNoteListUIDomainMapper(mapper: NoteListUIDomainMapper.Base): NoteListUIDomainMapper
}