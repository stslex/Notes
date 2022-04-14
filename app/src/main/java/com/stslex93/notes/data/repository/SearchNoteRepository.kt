package com.stslex93.notes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.mapper.NotePagingEntityDataMapper
import com.stslex93.notes.data.model.NoteDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface SearchNoteRepository {

    fun setQuery(query: String)

    @ExperimentalCoroutinesApi
    val notes: Flow<PagingData<NoteDataModel>>

    class Base @Inject constructor(
        private val noteDao: NoteDao,
        private val mapper: NotePagingEntityDataMapper
    ) : SearchNoteRepository {

        private val _query: MutableStateFlow<String> = MutableStateFlow("")
        private val query: StateFlow<String> = _query.asStateFlow()

        override fun setQuery(query: String) {
            _query.tryEmit(query)
        }

        @ExperimentalCoroutinesApi
        override val notes: Flow<PagingData<NoteDataModel>> = query.map(::newPager)
            .flatMapLatest { pager -> pager.flow.map(mapper::map) }

        private fun newPager(query: String): Pager<Int, NoteEntity> {
            return Pager(pagingConfig) {
                newPagingSource?.invalidate()
                noteDao.getAll(query).also { newPagingSource = it }
            }
        }

        private var newPagingSource: PagingSource<*, *>? = null

        private val pagingConfig: PagingConfig by lazy {
            PagingConfig(PAGE_SIZE, enablePlaceholders = false)
        }


        companion object {
            private const val PAGE_SIZE: Int = 10
        }
    }
}