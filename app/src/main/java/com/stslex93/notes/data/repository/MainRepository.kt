package com.stslex93.notes.data.repository

import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface MainRepository {

    suspend fun getAllNotes(): Flow<Resource<List<Note>>>

    class Base @Inject constructor(
        private val dao: NoteDao
    ) : MainRepository {

        override suspend fun getAllNotes(): Flow<Resource<List<Note>>> = try {
            dao.getAll().flatMapLatest { flowOf(Resource.Success(data = it)) }
        } catch (exception: Exception) {
            flowOf(Resource.Failure(exception = exception))
        }

    }
}