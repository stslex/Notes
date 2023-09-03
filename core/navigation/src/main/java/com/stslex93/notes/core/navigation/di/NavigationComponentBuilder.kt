package com.stslex93.notes.core.navigation.di

import androidx.navigation.NavHostController
import com.stslex93.notes.core.ui.di.NavigationApi

object NavigationComponentBuilder {

    fun build(
        navHostController: NavHostController
    ): NavigationApi = DaggerNavigationComponent
        .builder()
        .controller(navHostController)
        .build()
}