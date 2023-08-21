package com.stslex93.notes.data.mapper

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel

class NoteDataEntityMapper : Mapper.Data<NoteDataModel, NoteEntity> {

    override fun map(data: NoteDataModel): NoteEntity = with(data) {
        NoteEntity(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp
        )
    }
}