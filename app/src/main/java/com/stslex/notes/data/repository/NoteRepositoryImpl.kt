package com.stslex.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex.core.Resource
import com.stslex.notes.data.database.NoteDao
import com.stslex.notes.data.mapper.NoteDataEntityMapper
import com.stslex.notes.data.mapper.NoteEntityDataMapper
import com.stslex.notes.data.mapper.NoteListDataEntityMapper
import com.stslex.notes.data.mapper.NoteListEntityDataMapper
import com.stslex.notes.data.model.NoteDataModel
import com.stslex.notes.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val mapperToData: NoteEntityDataMapper,
    private val mapperDataToEntity: NoteDataEntityMapper,
    private val mapperListEntityData: NoteListEntityDataMapper,
    private val mapperListDataEntity: NoteListDataEntityMapper
) : NoteRepository {

    @ExperimentalCoroutinesApi
    override fun getAll(): Flow<Resource<List<NoteDataModel>>> = try {
        dao.getAll().flatMapLatest {
            flowOf(Resource.Success(mapperListEntityData.map(it)))
        }
    } catch (exception: Exception) {
        flowOf(Resource.Failure(exception))
    }

    @ExperimentalCoroutinesApi
    override fun getNote(id: Int): Flow<Resource<NoteDataModel>> = try {
        dao.getNote(id = id).flatMapLatest { note ->
            flowOf(Resource.Success(mapperToData.map(note)))
        }
    } catch (exception: Exception) {
        flowOf(Resource.Failure(exception))
    }

    @ExperimentalCoroutinesApi
    override fun getNotesById(ids: List<String>): Flow<Resource<List<NoteDataModel>>> = try {
        dao.getNotesById(ids = ids).flatMapLatest {
            flowOf(Resource.Success(mapperListEntityData.map(it)))
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
        dao.insertAll(notes = mapperListDataEntity.map(notes))
    }

    override suspend fun update(note: NoteDataModel) {
        dao.update(note = mapperDataToEntity.map(note))
    }
}