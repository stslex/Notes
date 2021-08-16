package com.stslex93.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.data.source.NoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val source: NoteDataSource) : NoteRepository {
    override fun getAll() = source.getAll()
    override fun getNote(id: String): Flow<Note> = source.getNote(id = id)
    override fun getNotesById(ids: List<String>): Flow<List<Note>> = source.getNotesById(ids = ids)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteNotesById(ids: List<String>) = withContext(Dispatchers.IO) {
        source.deleteNotesById(ids = ids)
    }

    override suspend fun insert(note: Note) = withContext(Dispatchers.IO) {
        source.insert(note = note)
    }

    override suspend fun insertAll(notes: List<Note>) = withContext(Dispatchers.IO) {
        source.insertAll(notes = notes)
    }

    override suspend fun update(note: Note) = withContext(Dispatchers.IO) {
        source.update(note = note)
    }
}