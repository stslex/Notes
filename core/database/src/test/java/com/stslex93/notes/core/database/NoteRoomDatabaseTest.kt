package com.stslex93.notes.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.stslex93.notes.core.database.database.NoteRoomDatabase
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NoteRoomDatabaseTest {

    private var _dao: NoteDao? = null
    private val dao: NoteDao
        get() = checkNotNull(_dao)

    init {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.databaseBuilder(
            context, NoteRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Test
    fun t1GetDao() {
        _dao = database.dao
        Assert.assertNotNull(dao)
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