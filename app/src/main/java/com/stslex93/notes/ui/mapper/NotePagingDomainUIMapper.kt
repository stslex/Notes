package com.stslex93.notes.ui.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.core.Mapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel
import java.util.concurrent.Executors
import javax.inject.Inject

interface NotePagingDomainUIMapper :
    Mapper.Data<PagingData<NoteDomainModel>, PagingData<NoteUIModel>> {

    class Base @Inject constructor(
        private val mapper: NoteDomainUIPrimaryMapper
    ) : NotePagingDomainUIMapper {

        override fun map(data: PagingData<NoteDomainModel>): PagingData<NoteUIModel> =
            data.map(Executors.newSingleThreadExecutor(), mapper::map)
    }
}