package com.stslex93.notes.domain.repository

import androidx.paging.PagingData
import com.stslex93.notes.data.model.NoteDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface SearchNoteRepository {

    fun setQuery(query: String)

    @ExperimentalCoroutinesApi
    val notes: Flow<PagingData<NoteDataModel>>
}