package com.stslex93.notes.ui.model

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.model.NoteData
import javax.inject.Inject

interface NoteUIDataMapper : Mapper.Data<NoteUI, NoteData> {

    class Base @Inject constructor() : NoteUIDataMapper {

        override fun map(data: NoteUI): NoteData = with(data) {
            NoteData.Base(
                id = getId(),
                title = getTitle(),
                content = getContent(),
                timestamp = getTimestamp()
            )
        }
    }
}