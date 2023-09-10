package com.stslex93.notes.feature.edit_label.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.core.appApi
import com.stslex93.notes.core.label.di.LabelApiBuilder
import com.stslex93.notes.core.notes.di.NoteApiBuilder
import com.stslex93.notes.core.ui.base.daggerViewModel
import com.stslex93.notes.core.ui.di.NavigationApi
import com.stslex93.notes.core.ui.di.navigationApi
import com.stslex93.notes.feature.edit_label.ui.EditLabelViewModel

object EditLabelBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): EditLabelComponent = DaggerEditLabelComponent
        .factory()
        .create(
            dependencies = DaggerEditLabelComponent_EditLabelDependenciesComponent
                .factory()
                .create(
                    navigationApi = navigationApi,
                    noteApi = NoteApiBuilder.build(appApi),
                    labelApi = LabelApiBuilder.build(appApi)
                )
        )
}

@Composable
fun initComponent(key: String): EditLabelViewModel {
    val context = LocalContext.current
    return daggerViewModel(key) {
        EditLabelBuilder
            .build(
                appApi = context.appApi,
                navigationApi = context.navigationApi
            )
            .viewModelFactory
    }
}