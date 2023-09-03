package com.stslex93.notes.feature.home.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.core.appApi
import com.stslex93.notes.core.notes.di.NoteApiBuilder
import com.stslex93.notes.core.ui.base.daggerViewModel
import com.stslex93.notes.core.ui.di.NavigationApi
import com.stslex93.notes.core.ui.di.navigationApi
import com.stslex93.notes.feature.home.ui.HomeViewModel

object HomeComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): HomeComponent = DaggerHomeComponent
        .factory()
        .create(
            dependencies = DaggerHomeComponent_HomeDependenciesComponent
                .factory()
                .create(
                    noteApi = NoteApiBuilder.build(appApi),
                    navigationApi = navigationApi
                )
        )
}


@Composable
fun setupComponent(): HomeViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        HomeComponentBuilder
            .build(
                appApi = context.appApi,
                navigationApi = context.navigationApi
            )
            .factory
    }
}