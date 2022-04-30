package com.stslex93.notes.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.core.Mapper
import java.util.concurrent.Executors
import javax.inject.Inject

class PagingMapper<F : Any, T : Any> @Inject constructor(
    private val mapper: Mapper.Data<F, T>
) : Mapper.Data<PagingData<F>, PagingData<T>> {

    override fun map(data: PagingData<F>): PagingData<T> =
        data.map(Executors.newSingleThreadExecutor(), mapper::map)
}