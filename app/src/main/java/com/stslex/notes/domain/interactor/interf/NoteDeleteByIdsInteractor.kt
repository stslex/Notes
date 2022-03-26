package com.stslex.notes.domain.interactor.interf

interface NoteDeleteByIdsInteractor {
    suspend fun invoke(idList: List<Int>)
}