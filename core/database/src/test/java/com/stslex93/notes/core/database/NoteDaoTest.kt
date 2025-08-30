package com.stslex93.notes.core.database

import androidx.paging.PagingSource
import com.ibm.icu.impl.Assert.fail
import com.stslex93.notes.core.database.note.NoteDao
import com.stslex93.notes.core.database.note.NoteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.robolectric.annotation.Config
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
@Config(application = BaseDatabaseTest.TestApplication::class)
internal class NoteDaoTest : BaseDatabaseTest() {

    private val dao: NoteDao get() = database.noteDao

    @BeforeEach
    override fun initDb() {
        super.initDb()
    }

    @AfterEach
    override fun clearDb() {
        super.clearDb()
    }

    @Test
    fun getAll() = runTest {
        dao.deleteAll()
        testListOfNotes.forEach { note ->
            dao.insert(note)
        }
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
        assertEquals(actual, mockedItems)
    }

    @Test
    fun getAllNotes() = runTest {
        dao.deleteAll()
        dao.insert(testNote)
        val notes = dao.getAllNotes()
        assertTrue(notes.isNotEmpty())
    }

    @Test
    fun getNoteFlow() = runTest {
        val expectedNote = testNote.copy(
            id = testNote.hashCode()
        )
        dao.insert(expectedNote)
        val compareNote = dao.getNoteFlow(expectedNote.id).first()
        assertEquals(expectedNote, compareNote)
    }

    @Test
    fun getNote() = runTest {
        val expectedNote = testNote.copy(
            id = testNote.hashCode()
        )
        dao.insert(expectedNote)
        val compareNote = dao.getNote(expectedNote.id)
        assertEquals(expectedNote, compareNote)
    }

    @Test
    fun getNotesById() = runTest {
        testListOfNotes.forEach { dao.insert(it) }
        val listOfIds = dao.getAllNotes().map { it.id.toString() }
        if (listOfIds.isEmpty()) {
            fail("List of ids is empty")
        }
        val compareListOfIds = dao.getNotesById(listOfIds).first().map { it.id.toString() }
        assertEquals(listOfIds, compareListOfIds)
    }

    @Test
    fun insertSingleNote() = runTest {
        val notesSize = dao.getAllNotes().size
        dao.insert(testNote)
        val notes = dao.getAllNotes()
        val notesSizeAssert = notesSize.plus(1)
        assertEquals(notesSizeAssert, notes.size)
        assertTrue(notes.containsCurrentItem)
    }

    @Test
    fun deleteNotesById() = runTest {
        testListOfNotes.forEach { dao.insert(it) }
        val listOfIds = dao.getAllNotes().map { it.id }
        if (listOfIds.isEmpty()) {
            fail("List of ids is empty")
        }
        testListOfNotes.forEach { dao.insert(it) }
        dao.deleteNotesById(listOfIds)
        val allNotes = dao.getAllNotes()
        val isNotContains = allNotes.isEmpty() || allNotes.any { listOfIds.contains(it.id).not() }
        assertTrue(isNotContains) { "Notes contains deleted ids" }
    }

    @Test
    fun insertAll() = runTest {
        val beforeInsert = dao.getAllNotes().size
        testListOfNotes.forEach { dao.insert(it) }
        val afterInsert = dao.getAllNotes().size
        assertNotEquals(beforeInsert, afterInsert)
    }

    @Test
    fun deleteAll() = runTest {
        val beforeInsert = dao.getAllNotes().size
        testListOfNotes.forEach { dao.insert(it) }
        val afterInsert = dao.getAllNotes().size
        assertNotEquals(beforeInsert, afterInsert)
        dao.deleteAll()
        assertTrue(dao.getAllNotes().isEmpty())
    }

    private val testListOfNotes: List<NoteEntity> by lazy {
        listOf(testNote, testNote, testNote, testNote)
    }

    private val List<NoteEntity>.containsCurrentItem: Boolean
        get() = contains(testNote.copy(id = last().id))

    private val testNote: NoteEntity by lazy {
        NoteEntity(0, "title", "content", System.currentTimeMillis(), emptySet())
    }

}