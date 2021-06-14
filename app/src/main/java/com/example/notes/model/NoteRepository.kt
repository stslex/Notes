package com.example.notes.model

import androidx.annotation.WorkerThread
import com.example.notes.model.base.Note
import com.example.notes.model.database.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAll()

    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun insertAll(notes: List<Note>){
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