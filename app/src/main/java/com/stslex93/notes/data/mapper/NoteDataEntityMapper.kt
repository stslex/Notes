package com.stslex93.notes.data.mapper

import com.stslex93.core.Mapper
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel
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