package com.stslex93.notes.domain.repository

import com.stslex93.core.Resource
import com.stslex93.notes.data.model.NoteDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    @ExperimentalCoroutinesApi
    fun getNote(id: Int): Flow<Resource<NoteDataModel>>

    suspend fun deleteNotesById(ids: List<Int>)
    suspend fun insert(note: NoteDataModel)
    suspend fun insertAll(notes: List<NoteDataModel>)
}