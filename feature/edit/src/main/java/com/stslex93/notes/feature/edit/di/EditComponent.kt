package com.stslex93.notes.feature.edit.di

import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.core.label.di.LabelApi
import com.stslex93.notes.core.notes.di.NoteApi
import com.stslex93.notes.core.ui.di.NavigationApi
import dagger.Component

@Component(
    dependencies = [EditDependencies::class],
    modules = [EditModule::class]
)
@EditScope
interface EditComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: EditDependencies): EditComponent
    }

    @Component(
        dependencies = [
            NoteApi::class,
            NavigationApi::class,
            LabelApi::class
        ]
    )
    interface EditDependenciesComponent : EditDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                noteApi: NoteApi,
                navigationApi: NavigationApi,
                labelApi: LabelApi
            ): EditDependencies
        }
    }

    val factory: ViewModelProvider.Factory
}