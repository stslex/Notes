package com.stslex.notes.data.mapper

import com.stslex.core.Mapper
import com.stslex.notes.data.entity.NoteEntity
import com.stslex.notes.data.model.NoteDataModel
import javax.inject.Inject

interface NoteEntityDataMapper : Mapper.Data<NoteEntity, NoteDataModel> {

    class Base @Inject constructor() : NoteEntityDataMapper {

        override fun map(data: NoteEntity): NoteDataModel = with(data) {
            NoteDataModel.Base(
                id = id,
                title = title,
                content = content,
                timestamp = timestamp
            )
        }
    }
}