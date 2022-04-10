package com.stslex.notes.data.mapper

import com.stslex.core.Mapper
import com.stslex.notes.data.entity.NoteEntity
import com.stslex.notes.data.model.NoteDataModel
import javax.inject.Inject

interface NoteListEntityDataMapper : Mapper.Data<List<NoteEntity>, List<NoteDataModel>> {

    class Base @Inject constructor(
        private val mapper: NoteEntityDataMapper
    ) : NoteListEntityDataMapper {

        override fun map(data: List<NoteEntity>): List<NoteDataModel> = data.map(mapper::map)
    }
}