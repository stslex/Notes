package com.stslex.notes.domain.interactor.interf

import androidx.paging.PagingData
import com.stslex.notes.domain.model.NoteDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface NoteGetAllWithQueryInteractor {

    @ExperimentalCoroutinesApi
    fun invoke(): Flow<PagingData<NoteDomainModel>>

    fun setQuery(query: String)
}