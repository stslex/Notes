package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex93.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteDeleteByIdsInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteDeleteByIdsInteractor {

    override suspend fun invoke(idList: List<Int>) = repository.deleteNotesById(idList)
}