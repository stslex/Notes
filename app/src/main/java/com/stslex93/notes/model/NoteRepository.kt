package com.stslex93.notes.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.stslex93.notes.model.base.Note
import com.stslex93.notes.model.database.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val theme = MutableLiveData<Boolean>()

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