package com.stslex93.notes.core.database.di

import com.stslex93.notes.core.core.AppApi
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [DatabaseDependencies::class],
    modules = [DatabaseModule::class]
)
@Singleton
interface DatabaseComponent : DatabaseApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: DatabaseDependencies): DatabaseApi
    }

    @Component(
        dependencies = [AppApi::class]
    )
    interface DatabaseComponentDependencies : DatabaseDependencies {

        @Component.Factory
        interface Factory {
            fun create(appApi: AppApi): DatabaseDependencies
        }
    }
}