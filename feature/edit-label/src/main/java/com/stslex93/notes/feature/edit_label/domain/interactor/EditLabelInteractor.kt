package com.stslex93.notes.feature.edit_label.domain.interactor

import androidx.paging.PagingData
import com.stslex93.notes.feature.edit_label.domain.model.LabelDomain
import kotlinx.coroutines.flow.Flow

interface EditLabelInteractor {

    fun searchLabels(query: String): Flow<PagingData<LabelDomain>>

    suspend fun createLabelAndAdd(
        noteIds: Set<Int>,
        label: LabelDomain
    )

    suspend fun addLabel(
        noteIds: Set<Int>,
        labelUuid: String
    )
}