package com.stslex93.notes.di.module

import com.stslex93.notes.data.mapper.NoteDataEntityMapper
import com.stslex93.notes.data.mapper.NoteEntityDataMapper
import com.stslex93.notes.data.mapper.NotePagingEntityDataMapper
import com.stslex93.notes.domain.mappers.*
import com.stslex93.notes.ui.mapper.*
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    /*Entity Data*/
    @Binds
    fun bindsNoteEntityDataMapper(mapper: NoteEntityDataMapper.Base): NoteEntityDataMapper

    /*Data Entity*/
    @Binds
    fun bindsNoteDataEntityMapper(mapper: NoteDataEntityMapper.Base): NoteDataEntityMapper

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

    /*PagingData*/
    @Binds
    fun bindsNotePagingEntityDataMapper(mapper: NotePagingEntityDataMapper.Base): NotePagingEntityDataMapper

    @Binds
    fun bindsNotePagingDataDomainMapper(mapper: NotePagingDataDomainMapper.Base): NotePagingDataDomainMapper

    @Binds
    fun bindsNotePagingDomainUIMapper(mapper: NotePagingDomainUIMapper.Base): NotePagingDomainUIMapper
}