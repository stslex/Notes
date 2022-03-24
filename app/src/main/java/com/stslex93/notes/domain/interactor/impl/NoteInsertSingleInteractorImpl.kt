package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.data.model.NoteData
import com.stslex93.notes.domain.interactor.interf.NoteInsertSingleInteractor
import com.stslex93.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteInsertSingleInteractor {
    override suspend fun invoke(note: NoteData) = repository.insert(note)
}