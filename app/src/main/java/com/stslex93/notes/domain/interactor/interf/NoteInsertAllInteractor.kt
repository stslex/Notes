package com.stslex93.notes.domain.interactor.interf

import com.stslex93.notes.data.model.NoteData

interface NoteInsertAllInteractor {
    suspend fun invoke(notes: List<NoteData>)
}