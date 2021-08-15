package com.stslex93.notes.data.source

import com.stslex93.notes.data.db.NoteDao
import com.stslex93.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteDataSourceImpl @Inject constructor(private val dao: NoteDao) : NoteDataSource {
    override fun getAll() = dao.getAll()
    override fun getNote(id: String): Flow<Note> = dao.getNote(id = id)
    override fun getNotesById(ids: List<String>) = dao.getNotesById(ids = ids)
    override suspend fun deleteNotesById(ids: List<String>) = dao.deleteNotesById(ids = ids)
    override suspend fun insertAll(notes: List<Note>) = dao.insertAll(notes = notes)
    override suspend fun insert(note: Note) = dao.insert(note = note)
    override suspend fun update(note: Note) = dao.update(note = note)
    override suspend fun deleteNotes(notes: List<Note>) = dao.deleteNotes(notes = notes)
}