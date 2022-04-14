package com.stslex93.notes.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.core.Mapper
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel
import java.util.concurrent.Executors
import javax.inject.Inject

interface NotePagingEntityDataMapper : Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>> {

    class Base @Inject constructor(
        private val mapper: NoteEntityDataMapper
    ) : NotePagingEntityDataMapper {

        override fun map(data: PagingData<NoteEntity>): PagingData<NoteDataModel> =
            data.map(Executors.newSingleThreadExecutor(), mapper::map)
    }
}