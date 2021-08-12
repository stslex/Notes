package com.stslex93.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex93.notes.data.model.Note
import com.stslex93.notes.data.source.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getAll()

    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun insertAll(notes: List<Note>) {
        noteDao.insertAll(notes)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun deleteNotes(notes: List<Note>) {
        noteDao.deleteNotes(notes)
    }
}