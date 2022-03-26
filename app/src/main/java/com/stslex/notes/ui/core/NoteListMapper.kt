package com.stslex.notes.ui.core

import com.stslex.notes.core.Mapper
import com.stslex.notes.core.Resource
import com.stslex.notes.data.entity.NoteEntity
import com.stslex.notes.ui.model.NoteUI
import javax.inject.Inject

interface NoteListMapper : Mapper.DataToUI<List<NoteEntity>, Resource<List<NoteUI>>> {

    class Base @Inject constructor() : NoteListMapper {

        override fun map(data: List<NoteEntity>): Resource<List<NoteUI>> = Resource.Success(
            data.map {
                NoteUI.Base(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    timestamp = it.timestamp
                )
            }
        )

        override fun map(exception: Exception): Resource<List<NoteUI>> = Resource.Failure(exception)

        override fun map(): Resource<List<NoteUI>> = Resource.Loading
    }
}