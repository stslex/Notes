package com.stslex.notes.ui.mapper

import com.stslex.core.Mapper
import com.stslex.core.Resource
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.ui.model.NoteUIModel
import javax.inject.Inject

interface NoteListDomainUIMapper :
    Mapper.DataToUI<List<NoteDomainModel>, Resource<List<NoteUIModel>>> {

    class Base @Inject constructor(
        private val mapper: NoteDomainUIPrimaryMapper
    ) : NoteListDomainUIMapper {

        override fun map(): Resource<List<NoteUIModel>> = Resource.Loading

        override fun map(exception: Exception): Resource<List<NoteUIModel>> =
            Resource.Failure(exception)

        override fun map(data: List<NoteDomainModel>): Resource<List<NoteUIModel>> =
            Resource.Success(data.map(mapper::map))
    }
}