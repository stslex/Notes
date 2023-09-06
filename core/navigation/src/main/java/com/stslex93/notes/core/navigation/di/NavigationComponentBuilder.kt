package com.stslex93.notes.core.navigation.di

import com.stslex93.notes.core.navigation.v2.controller.NavExtrasHostController
import com.stslex93.notes.core.ui.di.NavigationApi

object NavigationComponentBuilder {

    fun build(
        navController: NavExtrasHostController
    ): NavigationApi = DaggerNavigationComponent
        .builder()
        .controller(navController)
        .build()
}