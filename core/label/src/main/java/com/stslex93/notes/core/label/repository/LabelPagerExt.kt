package com.stslex93.notes.core.label.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.stslex93.notes.core.database.label.LabelEntity
import com.stslex93.notes.core.label.model.LabelDataMapper.toData
import com.stslex93.notes.core.label.model.LabelDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

object LabelPagerExt {

    const val PAGE_SIZE = 30
    const val IS_PLACEHOLDER_ENABLE = false

    inline fun getPagingLabels(
        crossinline action: () -> PagingSource<Int, LabelEntity>,
    ): Flow<PagingData<LabelDataModel>> = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = IS_PLACEHOLDER_ENABLE
        )
    ) {
        action()
    }
        .flow
        .map { pagingData ->
            pagingData.map { it.toData() }
        }
        .flowOn(Dispatchers.IO)
}