package com.stslex93.notes.data.model

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.core.Resource
import com.stslex93.notes.ui.model.NoteUI
import javax.inject.Inject

interface NoteListDataUIMapper : Mapper.DataToUI<List<NoteData>, Resource<List<NoteUI>>> {

    class Base @Inject constructor() : NoteListDataUIMapper {

        override fun map(exception: Exception): Resource<List<NoteUI>> =
            Resource.Failure(exception)

        override fun map(): Resource<List<NoteUI>> = Resource.Loading

        override fun map(data: List<NoteData>): Resource<List<NoteUI>> = Resource.Success(
            data.map {
                with(it) {
                    NoteUI.Base(
                        id = id(),
                        title = title(),
                        content = content(),
                        timestamp = timestamp()
                    )
                }
            }
        )
    }
}