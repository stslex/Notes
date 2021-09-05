package com.stslex93.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex93.notes.data.db.NoteDao
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.utilites.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao) : NoteRepository {

    override suspend fun getAll(): Flow<Response<List<Note>>> = flow {
        try {
            dao.getAll().collect {
                emit(Response.Success(it))
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getNote(id: String): Flow<Note> = dao.getNote(id = id)
    override fun getNotesById(ids: List<String>): Flow<List<Note>> = dao.getNotesById(ids = ids)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteNotesById(ids: List<String>) = withContext(Dispatchers.IO) {
        dao.deleteNotesById(ids = ids)
    }

    override suspend fun insert(note: Note) = withContext(Dispatchers.IO) {
        dao.insert(note = note)
    }

    override suspend fun insertAll(notes: List<Note>) = withContext(Dispatchers.IO) {
        dao.insertAll(notes = notes)
    }

    override suspend fun update(note: Note) = withContext(Dispatchers.IO) {
        dao.update(note = note)
    }
}