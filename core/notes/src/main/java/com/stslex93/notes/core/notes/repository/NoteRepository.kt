package com.stslex93.notes.core.notes.repository

import androidx.paging.PagingData
import com.stslex93.notes.core.notes.model.NoteDataModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun searchNotes(query: String): Flow<PagingData<NoteDataModel>>

    fun getNoteFlow(id: Int): Flow<NoteDataModel>

    suspend fun getNote(id: Int): NoteDataModel

    suspend fun deleteNotesById(ids: List<Int>)

    suspend fun insert(note: NoteDataModel)
}