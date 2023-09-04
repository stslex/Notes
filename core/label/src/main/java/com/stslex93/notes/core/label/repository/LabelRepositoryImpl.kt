package com.stslex93.notes.core.label.repository

import androidx.paging.PagingData
import com.stslex93.notes.core.database.label.LabelDao
import com.stslex93.notes.core.label.model.LabelDataMapper.toData
import com.stslex93.notes.core.label.model.LabelDataMapper.toEntity
import com.stslex93.notes.core.label.model.LabelDataModel
import com.stslex93.notes.core.label.repository.LabelPagerExt.getPagingLabels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LabelRepositoryImpl @Inject constructor(
    private val dao: LabelDao
) : LabelRepository {

    override val allLabels: Flow<PagingData<LabelDataModel>>
        get() = getPagingLabels {
            dao.getAll()
        }

    override suspend fun addLabel(label: LabelDataModel) {
        withContext(Dispatchers.IO) {
            dao.addLabel(label.toEntity())
        }
    }

    override suspend fun removeLabel(uuid: String) {
        withContext(Dispatchers.IO) {
            dao.removeLabel(uuid)
        }
    }

    override fun getLabel(
        uuid: String
    ): Flow<LabelDataModel> = dao.getLabel(uuid)
        .map { label ->
            label.toData()
        }
        .flowOn(Dispatchers.IO)

    override fun searchLabels(
        query: String
    ): Flow<PagingData<LabelDataModel>> = getPagingLabels {
        dao.search(query)
    }
}
