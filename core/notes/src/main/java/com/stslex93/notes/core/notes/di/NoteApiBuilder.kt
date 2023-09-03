package com.stslex93.notes.core.notes.di

import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.database.di.DatabaseApiBuilder

object NoteApiBuilder {

    fun build(
        appApi: AppApi
    ): NoteApi = DaggerNoteComponent
        .factory()
        .create(
            dependencies = DaggerNoteComponent_NoteComponentDependencies
                .factory()
                .create(
                    databaseApi = DatabaseApiBuilder.build(appApi)
                )
        )
}