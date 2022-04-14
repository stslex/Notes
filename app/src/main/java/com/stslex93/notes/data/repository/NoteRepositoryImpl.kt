package com.stslex93.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex93.core.Resource
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.mapper.NoteDataEntityMapper
import com.stslex93.notes.data.mapper.NoteEntityDataMapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val mapperToData: NoteEntityDataMapper,
    private val mapperDataToEntity: NoteDataEntityMapper
) : NoteRepository {

    @ExperimentalCoroutinesApi
    override fun getNote(id: Int): Flow<Resource<NoteDataModel>> = try {
        dao.getNote(id = id).flatMapLatest { note ->
            flowOf(Resource.Success(mapperToData.map(note)))
        }
    } catch (exception: Exception) {
        flowOf(Resource.Failure(exception))
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteNotesById(ids: List<Int>) {
        dao.deleteNotesById(ids = ids)
    }

    override suspend fun insert(note: NoteDataModel) {
        dao.insert(note = mapperDataToEntity.map(note))
    }

    override suspend fun insertAll(notes: List<NoteDataModel>) {
        dao.insertAll(notes.map(mapperDataToEntity::map))
    }
}