package com.stslex93.notes.domain.interactor.interf

interface NoteDeleteByIdsInteractor {
    suspend fun invoke(idList: List<Int>)
}