package com.stslex93.notes.domain.interactor.interf

import com.stslex93.notes.domain.model.NoteDomainModel
import kotlinx.coroutines.flow.StateFlow

interface NoteEditInteractor {

    val note: StateFlow<NoteDomainModel>

    suspend fun getNote(id: Int)

    suspend fun insert(note: NoteDomainModel)
}

