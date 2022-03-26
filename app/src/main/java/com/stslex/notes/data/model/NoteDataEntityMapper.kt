package com.stslex.notes.data.model

import com.stslex.notes.core.Mapper
import com.stslex.notes.data.entity.NoteEntity
import javax.inject.Inject

interface NoteDataEntityMapper : Mapper.Data<NoteData, NoteEntity> {

    class Base @Inject constructor() : NoteDataEntityMapper {

        override fun map(data: NoteData): NoteEntity = with(data) {
            NoteEntity(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}