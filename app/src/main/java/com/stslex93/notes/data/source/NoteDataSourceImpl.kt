package com.stslex93.notes.data.source

import com.stslex93.notes.data.db.NoteDao
import com.stslex93.notes.data.entity.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteDataSourceImpl @Inject constructor(private val dao: NoteDao) : NoteDataSource {
    override fun getAll() = dao.getAll()
    override fun getNote(id: String): Flow<Note> = dao.getNote(id = id)
    override fun getNotesById(ids: List<String>) = dao.getNotesById(ids = ids)
    override suspend fun deleteNotesById(ids: List<String>) = withContext(Dispatchers.IO) {
        dao.deleteNotesById(ids = ids)
    }

    override suspend fun insertAll(notes: List<Note>) = withContext(Dispatchers.IO) {
        dao.insertAll(notes = notes)
    }

    override suspend fun insert(note: Note) = withContext(Dispatchers.IO) {
        dao.insert(note = note)
    }

    override suspend fun update(note: Note) = withContext(Dispatchers.IO) {
        dao.update(note = note)
    }
}