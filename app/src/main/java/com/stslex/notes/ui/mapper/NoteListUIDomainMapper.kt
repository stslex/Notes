package com.stslex.notes.ui.mapper

import com.stslex.core.Mapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.ui.model.NoteUIModel
import javax.inject.Inject

interface NoteListUIDomainMapper : Mapper.Data<List<NoteUIModel>, List<NoteDomainModel>> {

    class Base @Inject constructor(
        private val mapper: NoteUIDomainMapper
    ) : NoteListUIDomainMapper {

        override fun map(data: List<NoteUIModel>): List<NoteDomainModel> = data.map(mapper::map)
    }
}