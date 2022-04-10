package com.stslex.notes.data.mapper

import com.stslex.core.Mapper
import com.stslex.notes.data.entity.NoteEntity
import com.stslex.notes.data.model.NoteDataModel
import javax.inject.Inject

interface NoteDataEntityMapper : Mapper.Data<NoteDataModel, NoteEntity> {

    class Base @Inject constructor() : NoteDataEntityMapper {

        override fun map(data: NoteDataModel): NoteEntity = with(data) {
            NoteEntity(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}