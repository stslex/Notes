package com.stslex93.notes.core.notes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.stslex93.notes.core.database.NoteDao
import com.stslex93.notes.core.database.NoteEntity
import com.stslex93.notes.core.database.NoteRoomDatabase
import com.stslex93.notes.core.notes.model.toData
import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.core.notes.repository.NoteRepositoryImpl
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
    private val dao: NoteDao

    init {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.databaseBuilder(
            context, NoteRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
        dao = database.dao
        repository = NoteRepositoryImpl(dao)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetNote() = runBlocking(Dispatchers.IO) {
        dao.insert(testNote)
        val expectedNote = dao.getNote(1).first().toData()
        val note = repository.getNote(1).first()
        Assert.assertEquals(expectedNote, note)
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
        repository.insert(testNote.toData())
        val actualSize = dao.getAllNotes().size
        Assert.assertEquals(expectedSize, actualSize)
    }

    @Test
    fun testInsertAll() = runBlocking(Dispatchers.IO) {
        dao.insertAll(testListOfNotes)
        val expectedNotes = dao.getAllNotes().map { it.toData() }
        dao.deleteAll()
        repository.insertAll(expectedNotes)
        val actualNotes = dao.getAllNotes().map { it.toData() }
        Assert.assertEquals(expectedNotes, actualNotes)
    }

    private val testListOfNotes: List<NoteEntity> by lazy {
        listOf(testNote, testNote, testNote, testNote)
    }

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