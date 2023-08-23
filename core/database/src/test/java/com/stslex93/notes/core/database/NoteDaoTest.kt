package com.stslex93.notes.core.database

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class NoteDaoTest {

    private val dao: NoteDao

    init {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.databaseBuilder(
            context, NoteRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
        dao = database.dao
    }

    @Test
    fun insertSingleNote() = runBlocking(Dispatchers.IO) {
        val notesSize = dao.getAllNotes().size
        dao.insert(testNote)
        val notes = dao.getAllNotes()
        val notesSizeAssert = notesSize.plus(1)
        Assert.assertEquals(notesSizeAssert, notes.size)
        Assert.assertTrue(notes.containsCurrentItem)
    }

    @Test
    fun getAllNotes() = runBlocking(Dispatchers.IO) {
        val notes = dao.getAllNotes()
        Assert.assertNotNull(notes)
    }

    @Test
    fun getNote() = runBlocking(Dispatchers.IO) {
        dao.insert(testNote)
        val checkingNote = dao.getAllNotes().first()
        val compareNote = dao.getNote(checkingNote.id).first()
        Assert.assertEquals(checkingNote, compareNote)
    }

    @Test
    fun getNotesById() = runBlocking(Dispatchers.IO) {
        dao.insertAll(testListOfNotes)
        val listOfIds = dao.getAllNotes().map { it.id.toString() }
        if (listOfIds.isEmpty()) {
            Assert.fail()
        }
        val compareListOfIds = dao.getNotesById(listOfIds).first().map { it.id.toString() }
        Assert.assertEquals(listOfIds, compareListOfIds)
    }

    @Test
    fun deleteNotesById() = runBlocking(Dispatchers.IO) {
        dao.insertAll(testListOfNotes)
        val listOfIds = dao.getAllNotes().map { it.id }
        if (listOfIds.isEmpty()) {
            Assert.fail()
        }
        dao.insertAll(testListOfNotes)
        dao.deleteNotesById(listOfIds)
        val allNotes = dao.getAllNotes()
        val isNotContains = allNotes.isEmpty() || allNotes.any { listOfIds.contains(it.id).not() }
        Assert.assertTrue("Notes contains deleted ids", isNotContains)
    }

    @Test
    fun insertAll() = runBlocking(Dispatchers.IO) {
        val beforeInsert = dao.getAllNotes().size
        dao.insertAll(testListOfNotes)
        val afterInsert = dao.getAllNotes().size
        Assert.assertNotEquals(beforeInsert, afterInsert)
    }

    @Test
    fun deleteAll() = runBlocking(Dispatchers.IO) {
        val beforeInsert = dao.getAllNotes().size
        dao.insertAll(testListOfNotes)
        val afterInsert = dao.getAllNotes().size
        Assert.assertNotEquals(beforeInsert, afterInsert)
        dao.deleteAll()
        Assert.assertTrue(dao.getAllNotes().isEmpty())
    }

    @Test
    fun getAll() = runBlocking(Dispatchers.IO) {
        dao.deleteAll()
        dao.insertAll(testListOfNotes)
        val mockedItems = dao.getAllNotes()
        val pagingSource = dao.getAll("")
        val loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = mockedItems.size,
                placeholdersEnabled = false
            )
        )
        val actual = (loadResult as? PagingSource.LoadResult.Page)?.data
        Assert.assertEquals(actual, mockedItems)
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