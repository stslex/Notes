package com.stslex93.notes.data.database

import com.stslex93.notes.data.database.base.BaseRoomCallback
import com.stslex93.notes.data.database.base.BaseRoomDatabase
import com.stslex93.notes.data.entity.NoteEntity
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope

class NoteDatabaseCallback(
    scope: CoroutineScope,
    noteRoomDatabase: Lazy<BaseRoomDatabase<NoteDao>>
) : BaseRoomCallback<NoteDao>(scope, noteRoomDatabase) {

    override suspend fun populateDatabase(dao: NoteDao) {
        val note = NoteEntity(0, INITIAL_TITLE, INITIAL_CONTENT, System.currentTimeMillis())
        dao.insert(note)
    }

    companion object {
        private const val INITIAL_TITLE: String = "example note"
        private const val INITIAL_CONTENT: String =
            "Android 12 foregrounds the design language of Material You through updated widget styles, a dynamic color system, custom shapes, and motion enhancements. \n" +
                    "\n" +
                    "Starting with Android 12 on Pixel devices, users can personalize their phone with dynamic color by generating unique color experiences from any wallpaper image. Extracted colors can be applied across the entire OS, from the notification shade to the lock screen, to volume controls, and more."
    }
}