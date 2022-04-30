package com.stslex93.notes.data.repository

import android.content.Context
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stslex93.core.Mapper
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.database.NoteRoomDatabase
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.mapper.NoteEntityDataMapper
import com.stslex93.notes.data.mapper.PagingMapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.repository.SearchNoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.util.concurrent.atomic.AtomicBoolean


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SearchNoteRepositoryImplTest {

    @Before
    fun beforeSingleTest() {
        assertFlag.set(false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun t1SetQuery() = runBlocking {
        dao.insertAll(testListOfNotes)
        repository.setQuery(QUERY_TEST_TITLE)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun t2Notes() = runBlocking {
        repository.notes
        repository.setQuery(QUERY_TEST_TITLE)
    }

    private val testListOfNotes: List<NoteEntity> by lazy {
        listOf(
            testNote.copy(title = QUERY_TEST_TITLE),
            testNote.copy(content = QUERY_TEST_CONTENT),
            testNote,
            testNote
        )
    }

    private val testNote: NoteEntity by lazy {
        NoteEntity(0, "title", "content", System.currentTimeMillis())
    }

    companion object {

        private const val QUERY_TEST_TITLE = "query_test_title"

        private const val QUERY_TEST_CONTENT = "query_test_content"

        private const val DATABASE_NAME: String = "note_database"

        @JvmStatic
        private val assertFlag: AtomicBoolean = AtomicBoolean(false)

        @JvmStatic
        private lateinit var repository: SearchNoteRepository

        @JvmStatic
        private lateinit var db: NoteRoomDatabase

        @JvmStatic
        private lateinit var dao: NoteDao

        @BeforeClass
        @JvmStatic
        fun initTests() {
            val context: Context = ApplicationProvider.getApplicationContext()
            db = Room.databaseBuilder(context, NoteRoomDatabase::class.java, DATABASE_NAME).build()
            dao = db.dao()
            val pagingConfig = PagingConfig(10)
            repository = SearchNoteRepositoryImpl(dao, notePagingEntityDataMapper, pagingConfig)
        }

        private val notePagingEntityDataMapper: Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>> by lazy {
            val noteEntityDataMapper: Mapper.Data<NoteEntity, NoteDataModel> =
                NoteEntityDataMapper()
            PagingMapper(noteEntityDataMapper)
        }

        @AfterClass
        @JvmStatic
        fun endTests() {
            db.close()
        }
    }
}