package com.stslex93.notes.di.module

import androidx.paging.PagingData
import com.stslex93.notes.core.Mapper
import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.mapper.NoteDataEntityMapper
import com.stslex93.notes.data.mapper.NoteEntityDataMapper
import com.stslex93.notes.data.mapper.PagingMapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.mappers.NoteDataDomainMapper
import com.stslex93.notes.domain.mappers.NoteDataDomainPrimaryMapper
import com.stslex93.notes.domain.mappers.NoteDomainDataMapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.mapper.NoteDomainUIMapper
import com.stslex93.notes.ui.model.mapper.NoteDomainUIPrimaryMapper
import com.stslex93.notes.ui.model.mapper.NoteUIDomainMapper
import com.stslex93.notes.ui.model.NoteUIModel
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    /*Entity Data*/
    @Provides
    fun providesNoteEntityDataMapper(): Mapper.Data<NoteEntity, NoteDataModel> =
        NoteEntityDataMapper()

    /*Data Entity*/
    @Provides
    fun providesNoteDataEntityMapper(): Mapper.Data<NoteDataModel, NoteEntity> =
        NoteDataEntityMapper()


    /*Data Domain*/
    @Provides
    fun providesNoteDataDomainPrimaryMapper(): Mapper.Data<NoteDataModel, NoteDomainModel> =
        NoteDataDomainPrimaryMapper()

    @Provides
    fun bindsNoteDataDomainMapper(
        mapper: Mapper.Data<NoteDataModel, NoteDomainModel>
    ): Mapper.DataToUI<NoteDataModel, Resource<NoteDomainModel>> = NoteDataDomainMapper(mapper)

    /*Domain Data*/
    @Provides
    fun providesNoteDomainDataMapper(): Mapper.Data<NoteDomainModel, NoteDataModel> =
        NoteDomainDataMapper()

    /*Domain UI*/
    @Provides
    fun providesNoteDomainUIPrimaryMapper(): Mapper.Data<NoteDomainModel, NoteUIModel> =
        NoteDomainUIPrimaryMapper()

    @Provides
    fun providesNoteDomainUIMapper(
        mapper: Mapper.Data<NoteDomainModel, NoteUIModel>
    ): Mapper.DataToUI<NoteDomainModel, Resource<NoteUIModel>> = NoteDomainUIMapper(mapper)

    /*UI Domain*/
    @Provides
    fun providesNoteUIDomainMapper(): Mapper.Data<NoteUIModel, NoteDomainModel> =
        NoteUIDomainMapper()

    /*PagingData*/

    @Provides
    fun providesNotePagingDataEntityMapper(
        mapper: Mapper.Data<NoteEntity, NoteDataModel>
    ): Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>> = PagingMapper(mapper)

    @Provides
    fun providesNotePagingDataDomainMapper(
        mapper: Mapper.Data<NoteDataModel, NoteDomainModel>
    ): Mapper.Data<PagingData<NoteDataModel>, PagingData<NoteDomainModel>> = PagingMapper(mapper)

    @Provides
    fun providesNotePagingDomainUIMapper(
        mapper: Mapper.Data<NoteDomainModel, NoteUIModel>
    ): Mapper.Data<PagingData<NoteDomainModel>, PagingData<NoteUIModel>> = PagingMapper(mapper)

}