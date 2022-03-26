package com.stslex.notes.data.entity

import com.stslex.notes.core.Mapper
import com.stslex.notes.data.model.NoteData
import javax.inject.Inject

interface NoteEntityDataMapper : Mapper.Data<NoteEntity, NoteData> {

    class Base @Inject constructor() : NoteEntityDataMapper {

        override fun map(data: NoteEntity): NoteData = with(data) {
            NoteData.Base(
                id = id,
                title = title,
                content = content,
                timestamp = timestamp
            )
        }
    }
}