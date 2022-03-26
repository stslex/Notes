package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.core.Resource
import com.stslex.notes.data.model.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteGetAllInteractor {
    suspend fun invoke(): Flow<Resource<List<NoteData>>>
}