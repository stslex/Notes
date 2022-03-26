package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteDeleteByIdsInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteDeleteByIdsInteractor {

    override suspend fun invoke(idList: List<Int>) = repository.deleteNotesById(idList)
}