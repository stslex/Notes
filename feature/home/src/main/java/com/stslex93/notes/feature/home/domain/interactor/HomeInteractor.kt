package com.stslex93.notes.feature.home.domain.interactor

import androidx.paging.PagingData
import com.stslex93.notes.feature.home.domain.model.NoteDomain
import kotlinx.coroutines.flow.Flow

interface HomeInteractor {

    fun queryNotes(query: String): Flow<PagingData<NoteDomain>>

    fun setQuery(query: String)

    suspend fun deleteNotes(noteIds: List<Int>)
}