package com.stslex93.notes.domain.interactor.interf

fun interface NoteDeleteByIdsInteractor {
    suspend fun invoke(idList: List<Int>)
}