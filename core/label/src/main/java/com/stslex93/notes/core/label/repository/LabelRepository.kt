package com.stslex93.notes.core.label.repository

import androidx.paging.PagingData
import com.stslex93.notes.core.label.model.LabelDataModel
import kotlinx.coroutines.flow.Flow

interface LabelRepository {

    val allLabels: Flow<PagingData<LabelDataModel>>

    suspend fun addLabel(label: LabelDataModel)

    suspend fun removeLabel(uuid: String)

    fun getLabel(uuid: String): Flow<LabelDataModel>

    fun searchLabels(query: String): Flow<PagingData<LabelDataModel>>
}