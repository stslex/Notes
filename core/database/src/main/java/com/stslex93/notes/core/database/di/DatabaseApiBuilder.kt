package com.stslex93.notes.core.database.di

import com.stslex93.notes.core.core.AppApi

object DatabaseApiBuilder {

    fun build(
        appApi: AppApi
    ): DatabaseApi = DaggerDatabaseComponent
        .factory()
        .create(
            dependencies = DaggerDatabaseComponent_DatabaseComponentDependencies
                .factory()
                .create(appApi)
        )
}