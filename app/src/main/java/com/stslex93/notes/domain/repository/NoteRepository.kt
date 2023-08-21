package com.stslex93.notes.domain.repository

import com.stslex93.notes.data.model.NoteDataModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNote(id: Int): Flow<NoteDataModel>
    fun getLastNote(): Flow<NoteDataModel>
    suspend fun deleteNotesById(ids: List<Int>)
    suspend fun insert(note: NoteDataModel)
    suspend fun insertAll(notes: List<NoteDataModel>)
}