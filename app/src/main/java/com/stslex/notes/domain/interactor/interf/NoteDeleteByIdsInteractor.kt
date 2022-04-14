package com.stslex.notes.domain.interactor.interf

fun interface NoteDeleteByIdsInteractor {
    suspend fun invoke(idList: List<Int>)
}