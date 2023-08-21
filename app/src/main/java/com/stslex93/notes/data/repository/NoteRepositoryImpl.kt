package com.stslex93.notes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.data.model.toData
import com.stslex93.notes.data.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(
    private val dao: NoteDao,
) : NoteRepository {

    private var _query: MutableStateFlow<String> = MutableStateFlow("")
    private val query: StateFlow<String>
        get() = _query.asStateFlow()

    override fun getNote(id: Int): Flow<NoteDataModel> = dao.getNote(id = id)
        .map { it.toData() }
        .flowOn(Dispatchers.IO)

    override fun getLastNote(): Flow<NoteDataModel> = dao.getLastNote()
        .map { it.toData() }
        .flowOn(Dispatchers.IO)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val searchNotes: Flow<PagingData<NoteDataModel>>
        get() = query.flatMapLatest { query ->
            Pager(
                PagingConfig(
                    pageSize = 15,
                    enablePlaceholders = false
                )
            ) { dao.getAll(query) }
                .flow
        }
            .map { pagingData ->
                pagingData.map { it.toData() }
            }
            .flowOn(Dispatchers.IO)

    override fun search(query: String) {
        _query.value = query
    }

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