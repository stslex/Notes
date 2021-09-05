package com.stslex93.notes.data.repository

import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.utilites.Response
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getAll(): Flow<Response<List<Note>>>
    fun getNote(id: String): Flow<Note>
    fun getNotesById(ids: List<String>): Flow<List<Note>>
    suspend fun deleteNotesById(ids: List<String>)
    suspend fun insert(note: Note)
    suspend fun insertAll(notes: List<Note>)
    suspend fun update(note: Note)
}