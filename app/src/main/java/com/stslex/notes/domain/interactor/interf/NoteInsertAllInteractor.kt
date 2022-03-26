package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.data.model.NoteData

interface NoteInsertAllInteractor {
    suspend fun invoke(notes: List<NoteData>)
}