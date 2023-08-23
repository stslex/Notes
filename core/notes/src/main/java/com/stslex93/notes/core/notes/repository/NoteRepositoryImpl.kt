package com.stslex93.notes.core.notes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.notes.core.database.NoteDao
import com.stslex93.notes.core.notes.model.NoteDataModel
import com.stslex93.notes.core.notes.model.toData
import com.stslex93.notes.core.notes.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(
    private val dao: NoteDao,
) : NoteRepository {

    override fun getNote(id: Int): Flow<NoteDataModel> = dao.getNote(id = id)
        .map { it.toData() }
        .flowOn(Dispatchers.IO)

    override fun getLastNote(): Flow<NoteDataModel> = dao.getLastNote()
        .map { it.toData() }
        .flowOn(Dispatchers.IO)

    override fun searchNotes(
        query: String
    ): Flow<PagingData<NoteDataModel>> =
        Pager(
            PagingConfig(
                pageSize = 15,
                enablePlaceholders = false
            )
        ) {
            dao.getAll(query)
        }
            .flow
            .map { pagingData ->
                pagingData.map { it.toData() }
            }
            .flowOn(Dispatchers.IO)

    override suspend fun deleteNotesById(ids: List<Int>) {
        withContext(Dispatchers.IO) {
            dao.deleteNotesById(ids = ids)
        }
    }

    override suspend fun insert(note: NoteDataModel) {
        withContext(Dispatchers.IO) {
            dao.insert(note = note.toEntity())
        }
    }

    override suspend fun insertAll(notes: List<NoteDataModel>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(notes.map { it.toEntity() })
        }
    }
}