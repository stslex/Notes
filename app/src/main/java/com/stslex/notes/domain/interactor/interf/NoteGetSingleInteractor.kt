package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.core.Resource
import com.stslex.notes.data.model.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteGetSingleInteractor {
    fun invoke(id: Int): Flow<Resource<NoteData>>
}