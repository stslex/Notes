package com.stslex93.notes.feature.edit.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.core.appApi
import com.stslex93.notes.core.label.di.LabelApiBuilder
import com.stslex93.notes.core.notes.di.NoteApiBuilder
import com.stslex93.notes.core.ui.base.daggerViewModel
import com.stslex93.notes.core.ui.di.NavigationApi
import com.stslex93.notes.core.ui.di.navigationApi
import com.stslex93.notes.feature.edit.ui.EditNoteViewModel

object EditComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): EditComponent = DaggerEditComponent
        .factory()
        .create(
            dependencies = DaggerEditComponent_EditDependenciesComponent
                .factory()
                .create(
                    noteApi = NoteApiBuilder.build(appApi),
                    navigationApi = navigationApi,
                    labelApi = LabelApiBuilder.build(appApi)
                )
        )
}

@Composable
fun setupComponent(key: String): EditNoteViewModel {
    val context = LocalContext.current
    return daggerViewModel(key) {
        EditComponentBuilder
            .build(
                appApi = context.appApi,
                navigationApi = context.navigationApi
            )
            .factory
    }
}