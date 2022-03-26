package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.data.model.NoteData
import com.stslex.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertAllInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteInsertAllInteractor {

    override suspend fun invoke(notes: List<NoteData>) = repository.insertAll(notes)
}