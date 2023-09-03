package com.stslex93.notes.di.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex93.notes.core.core.AppApi
import com.stslex93.notes.core.core.appApi
import com.stslex93.notes.core.ui.di.NavigationApi

object MainComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): MainComponent = DaggerMainComponent
        .factory()
        .create(
            DaggerMainComponent_MainDependenciesComponent.factory()
                .create(
                    appApi = appApi,
                    navigationApi = navigationApi
                )
        )

    @Composable
    fun Build(navigationApi: NavigationApi) {
        val context = LocalContext.current
        build(
            appApi = context.appApi,
            navigationApi = navigationApi
        )
    }
}
