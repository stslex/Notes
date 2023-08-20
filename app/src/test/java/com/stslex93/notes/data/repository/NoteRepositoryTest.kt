package com.stslex93.notes.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.stslex93.notes.core.Mapper
import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.database.NoteRoomDatabase
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.mapper.NoteDataEntityMapper
import com.stslex93.notes.data.mapper.NoteEntityDataMapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.repository.NoteRepository
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NoteRepositoryTest : TestCase() {

    private val repository: NoteRepository
    private val mapperToData: Mapper.Data<NoteEntity, NoteDataModel>
    private val mapperDataToEntity: Mapper.Data<NoteDataModel, NoteEntity>
    private val dao: NoteDao

    init {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.databaseBuilder(
            context, NoteRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
        dao = database.dao()
        mapperToData = NoteEntityDataMapper()
        mapperDataToEntity = NoteDataEntityMapper()
        repository = NoteRepositoryImpl(dao, mapperToData, mapperDataToEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetNote() = runBlocking(Dispatchers.IO) {
        dao.insert(testNote)
        val expectedNote = mapperToData.map(dao.getNote(1).first())
        when (val actual = repository.getNote(1).first()) {
            is Resource.Success -> {
                Assert.assertEquals(expectedNote, actual.data)
            }
            is Resource.Failure -> {
                Assert.fail(actual.exception.message)
            }
        }
    }

    @Test
    fun testDeleteNotesById() = runBlocking(Dispatchers.IO) {
        dao.insertAll(testListOfNotes)
        val notes = dao.getAllNotes()
        val idsToDelete = notes.takeLast(3).map { it.id }
        repository.deleteNotesById(idsToDelete)
        val newNotes = dao.getAllNotes()
        val isDeleted = !newNotes.map { it.id }.containsAll(idsToDelete)
        Assert.assertTrue(isDeleted)
    }

    @Test
    fun testInsert() = runBlocking(Dispatchers.IO) {
        val expectedSize = dao.getAllNotes().size.plus(1)
        repository.insert(mapperToData.map(testNote))
        val actualSize = dao.getAllNotes().size
        Assert.assertEquals(expectedSize, actualSize)
    }

    @Test
    fun testInsertAll() = runBlocking(Dispatchers.IO) {
        dao.insertAll(testListOfNotes)
        val expectedNotes = dao.getAllNotes().map(mapperToData::map)
        dao.deleteAll()
        repository.insertAll(expectedNotes)
        val actualNotes = dao.getAllNotes().map(mapperToData::map)
        Assert.assertEquals(expectedNotes, actualNotes)
    }

    private val testListOfNotes: List<NoteEntity> by lazy {
        listOf(testNote, testNote, testNote, testNote)
    }

    private val List<NoteEntity>.containsCurrentItem: Boolean
        get() = contains(testNote.copy(id = last().id))

    private val testNote: NoteEntity by lazy {
        NoteEntity(0, "title", "content", System.currentTimeMillis())
    }

    companion object {
        private const val DATABASE_NAME: String = "note_database"
        private lateinit var database: NoteRoomDatabase

        @AfterClass
        @JvmStatic
        fun afterTestsEnd() {
            database.close()
        }
    }
}