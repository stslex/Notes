package com.stslex93.notes.core.notes.repository

import androidx.paging.PagingData
import com.stslex93.notes.core.notes.model.NoteDataModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    val searchNotes: Flow<PagingData<NoteDataModel>>

    fun getNote(id: Int): Flow<NoteDataModel>

    fun getLastNote(): Flow<NoteDataModel>

    fun search(query: String)

    suspend fun deleteNotesById(ids: List<Int>)

    suspend fun insert(note: NoteDataModel)

    suspend fun insertAll(notes: List<NoteDataModel>)
}