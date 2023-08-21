package com.stslex93.notes.domain.interactor.interf

import androidx.paging.PagingData
import com.stslex93.notes.domain.model.NoteDomainModel
import kotlinx.coroutines.flow.Flow

interface MainScreenInteractor {

    val searchNotes: Flow<PagingData<NoteDomainModel>>

    fun search(query: String)

    suspend fun insertAll(notes: List<NoteDomainModel>)

    suspend fun deleteAll(notes: List<Int>)
}