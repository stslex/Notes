package com.stslex93.notes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.repository.SearchNoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchNoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val mapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>>,
    private val pagingConfig: PagingConfig
) : SearchNoteRepository {

    private val _query: MutableStateFlow<String> = MutableStateFlow(INITIAL_QUERY)
    private val query: StateFlow<String>
        get() = _query.asStateFlow()

    private val newPager: Flow<Pager<Int, NoteEntity>> by lazy {
        query.map {
            Pager(pagingConfig) { noteDao.getAll(it) }
        }
    }

    override fun setQuery(query: String) {
        _query.tryEmit(query)
    }

    @ExperimentalCoroutinesApi
    override val notes: Flow<PagingData<NoteDataModel>> = newPager
        .flatMapLatest { pager -> pager.flow.map(mapper::map) }

    companion object {
        private const val INITIAL_QUERY: String = ""
    }
}