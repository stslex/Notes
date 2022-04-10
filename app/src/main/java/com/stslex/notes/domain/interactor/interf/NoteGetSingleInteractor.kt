package com.stslex.notes.domain.interactor.interf

import com.stslex.core.Resource
import com.stslex.notes.domain.model.NoteDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface NoteGetSingleInteractor {

    @ExperimentalCoroutinesApi
    fun invoke(id: Int): Flow<Resource<NoteDomainModel>>
}