package com.stslex93.notes.data.entity

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.model.NoteData
import javax.inject.Inject

interface NoteEntityDataMapper : Mapper.Data<Note, NoteData> {

    class Base @Inject constructor() : NoteEntityDataMapper {

        override fun map(data: Note): NoteData = with(data) {
            NoteData.Base(
                id = id,
                title = title,
                content = content,
                timestamp = timestamp
            )
        }
    }
}