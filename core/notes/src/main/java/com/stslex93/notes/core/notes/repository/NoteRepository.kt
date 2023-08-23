package com.stslex93.notes.core.notes.repository

import androidx.paging.PagingData
import com.stslex93.notes.core.notes.model.NoteDataModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun searchNotes(query: String): Flow<PagingData<NoteDataModel>>

    fun getNote(id: Int): Flow<NoteDataModel>

    fun getLastNote(): Flow<NoteDataModel>

    suspend fun deleteNotesById(ids: List<Int>)

    suspend fun insert(note: NoteDataModel)

    suspend fun insertAll(notes: List<NoteDataModel>)
}