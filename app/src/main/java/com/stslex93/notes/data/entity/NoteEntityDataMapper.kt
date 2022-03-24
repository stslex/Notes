package com.stslex93.notes.data.entity

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.model.NoteData
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