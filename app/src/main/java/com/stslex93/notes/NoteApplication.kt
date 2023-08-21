package com.stslex93.notes

import android.app.Application
import com.stslex93.notes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    override fun onCreate() {
        setUpKoin()
        super.onCreate()
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NoteApplication)
            modules(appModule)
        }
    }
}