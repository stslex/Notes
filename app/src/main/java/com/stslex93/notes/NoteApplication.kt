package com.stslex93.notes

import android.app.Application
import android.content.Context
import com.stslex93.notes.di.component.AppComponent
import com.stslex93.notes.di.component.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NoteApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).create()
    }
}

@ExperimentalCoroutinesApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is NoteApplication -> appComponent
        else -> (applicationContext as NoteApplication).appComponent
    }