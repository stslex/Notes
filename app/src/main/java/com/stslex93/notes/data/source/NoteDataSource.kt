package com.stslex93.notes.data.source

import com.stslex93.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    fun getAll(): Flow<List<Note>>
    suspend fun insertAll(notes: List<Note>)
    suspend fun insert(note: Note)
    suspend fun update(note: Note)
    suspend fun deleteNotes(notes: List<Note>)
}