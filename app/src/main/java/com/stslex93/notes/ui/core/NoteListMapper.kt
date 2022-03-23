package com.stslex93.notes.ui.core

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.ui.model.NoteUI
import javax.inject.Inject

interface NoteListMapper : Mapper.DataToUI<List<Note>, Resource<List<NoteUI>>> {

    class Base @Inject constructor() : NoteListMapper {

        override fun map(data: List<Note>): Resource<List<NoteUI>> = Resource.Success(
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