package com.stslex93.notes.ui.mapper

import com.stslex93.core.Mapper
import com.stslex93.core.Resource
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel
import javax.inject.Inject

class NoteDomainUIMapper @Inject constructor(
    private val mapper: Mapper.Data<NoteDomainModel, NoteUIModel>
) : Mapper.DataToUI<NoteDomainModel, Resource<NoteUIModel>> {

    override fun map(): Resource<NoteUIModel> = Resource.Loading

    override fun map(exception: Exception): Resource<NoteUIModel> = Resource.Failure(exception)

    override fun map(data: NoteDomainModel): Resource<NoteUIModel> =
        Resource.Success(mapper.map(data))
}