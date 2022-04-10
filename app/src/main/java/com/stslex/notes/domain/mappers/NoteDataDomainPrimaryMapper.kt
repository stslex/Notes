package com.stslex.notes.domain.mappers

import com.stslex.core.Mapper
import com.stslex.notes.data.model.NoteDataModel
import com.stslex.notes.domain.model.NoteDomainModel
import javax.inject.Inject

interface NoteDataDomainPrimaryMapper : Mapper.Data<NoteDataModel, NoteDomainModel> {

    class Base @Inject constructor() : NoteDataDomainPrimaryMapper {

        override fun map(data: NoteDataModel): NoteDomainModel = with(data) {
            NoteDomainModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}