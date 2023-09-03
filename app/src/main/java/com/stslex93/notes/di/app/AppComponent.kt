package com.stslex93.notes.di.app

import android.content.Context
import com.stslex93.notes.NoteApplication
import com.stslex93.notes.core.core.AppApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent : AppApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(application: NoteApplication)
}
