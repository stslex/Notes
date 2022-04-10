package com.stslex.notes.ui.mapper

import com.stslex.core.Mapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.ui.model.NoteUIModel
import javax.inject.Inject

interface NoteDomainUIPrimaryMapper : Mapper.Data<NoteDomainModel, NoteUIModel> {

    class Base @Inject constructor() : NoteDomainUIPrimaryMapper {

        override fun map(data: NoteDomainModel): NoteUIModel = with(data) {
            NoteUIModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}