package com.stslex93.notes.ui.mapper

import com.stslex93.core.Mapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel
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