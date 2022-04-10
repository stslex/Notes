package com.stslex.notes.domain.repository

import com.stslex.core.Resource
import com.stslex.notes.data.model.NoteDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    @ExperimentalCoroutinesApi
    fun getAll(): Flow<Resource<List<NoteDataModel>>>

    @ExperimentalCoroutinesApi
    fun getNote(id: Int): Flow<Resource<NoteDataModel>>

    @ExperimentalCoroutinesApi
    fun getNotesById(ids: List<String>): Flow<Resource<List<NoteDataModel>>>

    suspend fun deleteNotesById(ids: List<Int>)
    suspend fun insert(note: NoteDataModel)
    suspend fun insertAll(notes: List<NoteDataModel>)
    suspend fun update(note: NoteDataModel)
}