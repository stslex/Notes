package com.stslex93.notes.data.repository

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val mapperToData: Mapper.Data<NoteEntity, NoteDataModel>,
    private val mapperDataToEntity: Mapper.Data<NoteDataModel, NoteEntity>
) : NoteRepository {

    override fun getNote(id: Int): Flow<NoteDataModel> = dao.getNote(id = id)
        .map(mapperToData::map)
        .flowOn(Dispatchers.IO)

    override fun getLastNote(): Flow<NoteDataModel> = dao.getLastNote()
        .map(mapperToData::map)
        .flowOn(Dispatchers.IO)

    override suspend fun deleteNotesById(ids: List<Int>) {
        dao.deleteNotesById(ids = ids)
    }

    override suspend fun insert(note: NoteDataModel) {
        withContext(Dispatchers.IO) {
            dao.insert(note = mapperDataToEntity.map(note))
        }
    }

    override suspend fun insertAll(notes: List<NoteDataModel>) {
        dao.insertAll(notes.map(mapperDataToEntity::map))
    }
}