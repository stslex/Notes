package com.stslex93.notes.data.repository

import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.data.entity.NoteEntityDataMapper
import com.stslex93.notes.data.model.NoteData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface MainScreenRepository {

    @ExperimentalCoroutinesApi
    suspend fun getAllNotes(): Flow<Resource<List<NoteData>>>
    suspend fun deleteAll()
    suspend fun deleteNotesByIds(ids: List<Int>)

    class Base @Inject constructor(
        private val dao: NoteDao,
        private val mapper: NoteEntityDataMapper
    ) : MainScreenRepository {

        @ExperimentalCoroutinesApi
        override suspend fun getAllNotes(): Flow<Resource<List<NoteData>>> = try {
            dao.getAll().flatMapLatest { list ->
                val result = list.map { it.map(mapper) }
                flowOf(Resource.Success(result))
            }
        } catch (exception: Exception) {
            flowOf(Resource.Failure(exception = exception))
        }

        override suspend fun deleteAll() {
            dao.deleteAll()
        }

        override suspend fun deleteNotesByIds(ids: List<Int>) {
            dao.deleteNotesById(ids)
        }
    }
}