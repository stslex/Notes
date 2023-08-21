package com.stslex93.notes.data.mapper

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel

class NoteEntityDataMapper : Mapper.Data<NoteEntity, NoteDataModel> {

    override fun map(data: NoteEntity): NoteDataModel = with(data) {
        NoteDataModel(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp
        )
    }
}