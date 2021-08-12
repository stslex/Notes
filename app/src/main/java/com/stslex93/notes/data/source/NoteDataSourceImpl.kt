package com.stslex93.notes.data.source

import com.stslex93.notes.data.db.NoteDao
import com.stslex93.notes.data.entity.Note
import javax.inject.Inject

class NoteDataSourceImpl @Inject constructor(private val dao: NoteDao) : NoteDataSource {
    override fun getAll() = dao.getAll()
    override suspend fun insertAll(notes: List<Note>) = dao.insertAll(notes)
    override suspend fun insert(note: Note) = dao.insert(note)
    override suspend fun update(note: Note) = dao.update(note)
    override suspend fun deleteNotes(notes: List<Note>) = dao.deleteNotes(notes)
}