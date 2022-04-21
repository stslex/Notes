package com.stslex93.notes.domain.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.core.Mapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel
import java.util.concurrent.Executors
import javax.inject.Inject

interface NotePagingDataDomainMapper :
    Mapper.Data<PagingData<NoteDataModel>, PagingData<NoteDomainModel>> {

    class Base @Inject constructor(
        private val mapper: NoteDataDomainPrimaryMapper
    ) : NotePagingDataDomainMapper {

        override fun map(data: PagingData<NoteDataModel>): PagingData<NoteDomainModel> =
            data.map(Executors.newSingleThreadExecutor(), mapper::map)
    }
}