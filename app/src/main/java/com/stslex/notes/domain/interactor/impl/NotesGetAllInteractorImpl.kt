package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.core.Resource
import com.stslex.notes.data.model.NoteData
import com.stslex.notes.domain.interactor.interf.NoteGetAllInteractor
import com.stslex.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesGetAllInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteGetAllInteractor {

    override suspend fun invoke(): Flow<Resource<List<NoteData>>> = repository.getAll()
}