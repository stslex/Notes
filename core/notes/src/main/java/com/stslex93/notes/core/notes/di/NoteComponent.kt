package com.stslex93.notes.core.notes.di

import com.stslex93.notes.core.database.di.DatabaseApi
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [NoteDependencies::class],
    modules = [NoteModule::class]
)
@Singleton
interface NoteComponent : NoteApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: NoteDependencies): NoteApi
    }

    @Component(dependencies = [DatabaseApi::class])
    interface NoteComponentDependencies : NoteDependencies {

        @Component.Factory
        interface Factory {
            fun create(databaseApi: DatabaseApi): NoteDependencies
        }
    }
}