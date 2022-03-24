package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.data.model.NoteData
import com.stslex93.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex93.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertAllInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteInsertAllInteractor {
    override suspend fun invoke(notes: List<NoteData>) = repository.insertAll(notes)
}