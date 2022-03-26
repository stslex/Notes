package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.data.model.NoteData
import com.stslex.notes.domain.interactor.interf.NoteInsertSingleInteractor
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteInsertSingleInteractor {

    override suspend fun invoke(note: NoteData) = repository.insert(note)
}