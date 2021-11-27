package com.stslex93.notes.data.model

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.entity.Note
import javax.inject.Inject

interface NoteDataEntityMapper : Mapper.Data<NoteData, Note> {

    class Base @Inject constructor() : NoteDataEntityMapper {

        override fun map(data: NoteData): Note = with(data) {
            Note(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}