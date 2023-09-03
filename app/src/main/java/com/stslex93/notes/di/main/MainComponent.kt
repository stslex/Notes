package com.stslex93.notes.di.main

import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.ui.di.NavigationApi
import dagger.Component

@Component(dependencies = [MainDependencies::class])
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }

    @Component(dependencies = [AppApi::class, NavigationApi::class])
    interface MainDependenciesComponent : MainDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                appApi: AppApi,
                navigationApi: NavigationApi
            ): MainDependenciesComponent
        }
    }
}

