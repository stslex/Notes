package com.stslex93.notes.data.repository

import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.entity.NoteEntityDataMapper
import com.stslex93.notes.data.model.NoteData
import com.stslex93.notes.data.model.NoteDataEntityMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface EditNoteRepository {

    @ExperimentalCoroutinesApi
    suspend fun getNoteById(id: String): Flow<Resource<NoteData>>
    suspend fun insertNote(note: NoteData)
    suspend fun updateNote(note: NoteData)

    class Base @Inject constructor(
        private val dao: NoteDao,
        private val mapper: NoteEntityDataMapper,
        private val sendMapper: NoteDataEntityMapper
    ) : EditNoteRepository {

        @ExperimentalCoroutinesApi
        override suspend fun getNoteById(id: String): Flow<Resource<NoteData>> = flow {
            try {
                dao.getNote(id).collect {
                    emit(Resource.Success(it.map(mapper)))
                }
            } catch (exception: Exception) {
                emit(Resource.Failure(exception))
            }
        }

        override suspend fun insertNote(note: NoteData) {
            dao.insert(note.mapToEntity(sendMapper))
        }

        override suspend fun updateNote(note: NoteData) {
            dao.update(note.mapToEntity(sendMapper))
        }
    }
}