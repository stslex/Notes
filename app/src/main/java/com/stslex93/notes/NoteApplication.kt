package com.stslex93.notes

import android.app.Application
import android.content.Context
import com.stslex93.notes.di.component.AppComponent
import com.stslex93.notes.di.component.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

class NoteApplication : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder().application(this).create()
    }
}

@ExperimentalCoroutinesApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is NoteApplication -> appComponent
        else -> (applicationContext as NoteApplication).appComponent
    }