package com.stslex93.notes.domain.interactor.interf

import com.stslex93.notes.core.Resource
import com.stslex93.notes.domain.model.NoteDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

fun interface NoteGetSingleInteractor {

    @ExperimentalCoroutinesApi
    fun invoke(id: Int): Flow<Resource<NoteDomainModel>>
}