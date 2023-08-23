package com.stslex93.notes.feature.home.domain.interactor

import androidx.paging.PagingData
import com.stslex93.notes.core.notes.model.NoteDataModel
import kotlinx.coroutines.flow.Flow

interface HomeInteractor {

    val queryNotes: Flow<PagingData<NoteDataModel>>

    fun setQuery(query: String)

    suspend fun deleteNote(noteIds: List<Int>)
}