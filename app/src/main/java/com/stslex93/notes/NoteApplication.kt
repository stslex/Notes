package com.stslex93.notes

import android.app.Application
import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.core.ApplicationApi
import com.stslex93.notes.di.app.AppComponent
import com.stslex93.notes.di.app.DaggerAppComponent

class NoteApplication : Application(), ApplicationApi {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    override val appApi: AppApi
        get() = appComponent

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }
}