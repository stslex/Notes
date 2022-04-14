package com.stslex93.notes.ui.mapper

import com.stslex93.core.Mapper
import com.stslex93.core.Resource
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel
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