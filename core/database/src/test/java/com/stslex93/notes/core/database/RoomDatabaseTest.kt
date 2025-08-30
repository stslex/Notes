package com.stslex93.notes.core.database

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.robolectric.annotation.Config
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
@Config(application = BaseDatabaseTest.TestApplication::class)
internal class RoomDatabaseTest : BaseDatabaseTest() {

    @BeforeEach
    override fun initDb() {
        super.initDb()
    }

    @AfterEach
    override fun clearDb() {
        super.clearDb()
    }

    @Test
    fun noteDao() {
        val noteDao = database.noteDao
        assertNotNull(noteDao)
    }

    @Test
    fun labelDao() {
        val labelDao = database.labelDao
        assertNotNull(labelDao)
    }
}