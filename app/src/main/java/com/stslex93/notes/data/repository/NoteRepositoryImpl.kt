package com.stslex93.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.data.source.NoteDataSource
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val source: NoteDataSource) : NoteRepository {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getAll() = source.getAll()
    override suspend fun insert(note: Note) = source.insert(note)
    override suspend fun insertAll(notes: List<Note>) = source.insertAll(notes)
    override suspend fun update(note: Note) = source.update(note)
    override suspend fun deleteNotes(notes: List<Note>) = source.deleteNotes(notes)
}