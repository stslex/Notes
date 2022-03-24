package com.stslex93.notes.domain.interactor.interf

import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.model.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteGetSingleInteractor {
    fun invoke(id: Int): Flow<Resource<NoteData>>
}