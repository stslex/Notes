package com.stslex93.notes.core.label.repository

import androidx.paging.PagingData
import com.stslex93.notes.core.database.label.LabelDao
import com.stslex93.notes.core.label.model.LabelDataMapper.toData
import com.stslex93.notes.core.label.model.LabelDataMapper.toEntity
import com.stslex93.notes.core.label.model.LabelDataModel
import com.stslex93.notes.core.label.repository.LabelPagerExt.getPagingLabels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LabelRepositoryImpl @Inject constructor(
    private val dao: LabelDao
) : LabelRepository {

    override suspend fun removeLabel(uuid: String) {
        withContext(Dispatchers.IO) {
            dao.removeLabel(uuid)
        }
    }

    override suspend fun addLabel(label: LabelDataModel) {
        withContext(Dispatchers.IO) {
            dao.addLabel(label.toEntity())
        }
    }

    override suspend fun getAllLabels(
        uuids: Set<String>
    ): Set<LabelDataModel> = withContext(Dispatchers.IO) {
        dao.getLabels(uuids).map { it.toData() }.toSet()
    }

    override fun searchLabels(
        query: String
    ): Flow<PagingData<LabelDataModel>> = getPagingLabels {
        dao.search(query)
    }
}
