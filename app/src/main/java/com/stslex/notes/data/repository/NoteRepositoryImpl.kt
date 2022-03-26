package com.stslex.notes.data.repository

import androidx.annotation.WorkerThread
import com.stslex.core.Resource
import com.stslex.notes.data.database.NoteDao
import com.stslex.notes.data.entity.NoteEntityDataMapper
import com.stslex.notes.data.model.NoteData
import com.stslex.notes.data.model.NoteDataEntityMapper
import com.stslex.notes.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val mapperToData: NoteEntityDataMapper,
    private val mapperDataToEntity: NoteDataEntityMapper
) : NoteRepository {

    override suspend fun getAll(): Flow<Resource<List<NoteData>>> = dao.getAll().map {
        try {
            Resource.Success(it.map(mapperToData::map))
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override fun getNote(id: Int): Flow<Resource<NoteData>> = dao.getNote(id = id).map {
        try {
            Resource.Success(mapperToData.map(it))
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }


    override fun getNotesById(ids: List<String>): Flow<List<NoteData>> =
        dao.getNotesById(ids = ids).map { list ->
            list.map { mapperToData.map(it) }
        }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteNotesById(ids: List<Int>) = withContext(Dispatchers.IO) {
        dao.deleteNotesById(ids = ids)
    }

    override suspend fun insert(note: NoteData) = withContext(Dispatchers.IO) {
        dao.insert(note = mapperDataToEntity.map(note))
    }

    override suspend fun insertAll(notes: List<NoteData>) = withContext(Dispatchers.IO) {
        dao.insertAll(notes = notes.map { mapperDataToEntity.map(it) })
    }

    override suspend fun update(note: NoteData) = withContext(Dispatchers.IO) {
        dao.update(note = mapperDataToEntity.map(note))
    }
}