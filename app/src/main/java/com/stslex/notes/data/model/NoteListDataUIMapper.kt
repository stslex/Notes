package com.stslex.notes.data.model

import com.stslex.core.Mapper
import com.stslex.core.Resource
import com.stslex.notes.ui.model.NoteUI
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