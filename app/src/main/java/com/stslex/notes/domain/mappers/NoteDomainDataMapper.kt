package com.stslex.notes.domain.mappers

import com.stslex.core.Mapper
import com.stslex.notes.data.model.NoteDataModel
import com.stslex.notes.domain.model.NoteDomainModel
import javax.inject.Inject

interface NoteDomainDataMapper : Mapper.Data<NoteDomainModel, NoteDataModel> {

    class Base @Inject constructor() : NoteDomainDataMapper {

        override fun map(data: NoteDomainModel): NoteDataModel = with(data) {
            NoteDataModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}