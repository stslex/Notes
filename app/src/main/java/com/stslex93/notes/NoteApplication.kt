package com.stslex93.notes

import android.app.Application
import com.stslex93.notes.di.component.AppComponent
import com.stslex93.notes.di.component.DaggerAppComponent

class NoteApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}