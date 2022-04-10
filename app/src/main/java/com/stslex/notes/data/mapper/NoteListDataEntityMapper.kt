package com.stslex.notes.data.mapper

import com.stslex.core.Mapper
import com.stslex.notes.data.entity.NoteEntity
import com.stslex.notes.data.model.NoteDataModel
import javax.inject.Inject

interface NoteListDataEntityMapper : Mapper.Data<List<NoteDataModel>, List<NoteEntity>> {

    class Base @Inject constructor(
        private val mapper: NoteDataEntityMapper
    ) : NoteListDataEntityMapper {

        override fun map(data: List<NoteDataModel>): List<NoteEntity> = data.map(mapper::map)
    }
}