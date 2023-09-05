package com.stslex93.notes.feature.edit_label.di

import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.core.label.di.LabelApi
import com.stslex93.notes.core.notes.di.NoteApi
import com.stslex93.notes.core.ui.di.NavigationApi
import dagger.Component

@Component(
    dependencies = [EditLabelDependencies::class],
    modules = [EditLabelModule::class]
)
@EditLabelScope
interface EditLabelComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: EditLabelDependencies): EditLabelComponent
    }

    @Component(
        dependencies = [NavigationApi::class, NoteApi::class, LabelApi::class]
    )
    interface EditLabelDependenciesComponent : EditLabelDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                navigationApi: NavigationApi,
                noteApi: NoteApi,
                labelApi: LabelApi
            ): EditLabelDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}