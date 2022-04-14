package com.stslex.notes.domain.repository

import com.stslex.core.Resource
import com.stslex.notes.data.model.NoteDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    @ExperimentalCoroutinesApi
    fun getNote(id: Int): Flow<Resource<NoteDataModel>>

    suspend fun deleteNotesById(ids: List<Int>)
    suspend fun insert(note: NoteDataModel)
}