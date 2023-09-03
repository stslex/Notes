package com.stslex93.notes.feature.home.di

import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.core.notes.di.NoteApi
import com.stslex93.notes.core.ui.di.NavigationApi
import com.stslex93.notes.core.ui.di.Navigator
import dagger.Component

@Component(
    dependencies = [HomeDependencies::class],
    modules = [HomeModule::class]
)
@HomeScope
interface HomeComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: HomeDependencies): HomeComponent
    }

    @Component(
        dependencies = [NoteApi::class, NavigationApi::class]
    )
    interface HomeDependenciesComponent : HomeDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                noteApi: NoteApi,
                navigationApi: NavigationApi
            ): HomeDependencies
        }
    }

    val factory: ViewModelProvider.Factory
}