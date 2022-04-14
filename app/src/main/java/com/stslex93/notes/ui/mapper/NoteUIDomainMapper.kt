package com.stslex93.notes.ui.mapper

import com.stslex93.core.Mapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel
import javax.inject.Inject

interface NoteUIDomainMapper : Mapper.Data<NoteUIModel, NoteDomainModel> {

    class Base @Inject constructor() : NoteUIDomainMapper {

        override fun map(data: NoteUIModel): NoteDomainModel = with(data) {
            NoteDomainModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}