package com.stslex93.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.data.source.NoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val source: NoteDataSource) : NoteRepository {
    override fun getAll() = source.getAll()
    override fun getNote(id: String): Flow<Note> = source.getNote(id = id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(note: Note) = source.insert(note = note)
    override suspend fun insertAll(notes: List<Note>) = source.insertAll(notes = notes)
    override suspend fun update(note: Note) = source.update(note = note)
    override suspend fun deleteNotes(notes: List<Note>) = source.deleteNotes(notes = notes)
}