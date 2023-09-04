package com.stslex93.notes.core.label.di

import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.database.di.DatabaseApiBuilder

object LabelApiBuilder {

    fun build(
        appApi: AppApi
    ): LabelApi = DaggerLabelComponent
        .factory()
        .create(
            dependencies = DaggerLabelComponent_LabelDependenciesComponent
                .factory()
                .create(
                    databaseApi = DatabaseApiBuilder.build(appApi)
                )
        )
}