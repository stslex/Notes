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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
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
                pageSize = PAGE_SIZE,
                enablePlaceholders = IS_PLACEHOLDER_ENABLE
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

    companion object {
        private const val PAGE_SIZE = 15
        private const val IS_PLACEHOLDER_ENABLE = false
    }
}