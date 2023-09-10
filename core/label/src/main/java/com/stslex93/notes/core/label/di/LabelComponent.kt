package com.stslex93.notes.core.label.di

import com.stslex93.notes.core.database.di.DatabaseApi
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [LabelDependencies::class],
    modules = [LabelModule::class]
)
@Singleton
interface LabelComponent : LabelApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: LabelDependencies): LabelApi
    }

    @Component(dependencies = [DatabaseApi::class])
    interface LabelDependenciesComponent : LabelDependencies {

        @Component.Factory
        interface Factory {
            fun create(databaseApi: DatabaseApi): LabelDependencies
        }
    }
}