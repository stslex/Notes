package com.stslex93.notes.data.db

import android.app.Application
import androidx.room.Room
import javax.inject.Inject

class NoteRoomDataBaseImpl {

    @Inject
    lateinit var application: Application

    val noteRoomDatabase: NoteRoomDatabase = Room.databaseBuilder(
        application.applicationContext,
        NoteRoomDatabase::class.java,
        "WeatherApp-DB"
    ).build()

}