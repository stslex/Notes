package com.stslex93.notes.di.module

import android.app.Application
import com.stslex93.notes.NoteApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {
    @Binds
    @Singleton
    fun bindApplication(app: NoteApplication): Application
}