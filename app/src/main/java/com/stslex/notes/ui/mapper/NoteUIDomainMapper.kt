package com.stslex.notes.ui.mapper

import com.stslex.core.Mapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.ui.model.NoteUIModel
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