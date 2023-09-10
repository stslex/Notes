package com.stslex93.notes.core.navigation.di

import com.stslex93.notes.core.navigation.v2.controller.NavExtrasHostController
import com.stslex93.notes.core.ui.di.NavigationApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface NavigationComponent : NavigationApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun controller(navController: NavExtrasHostController): Builder

        fun build(): NavigationApi
    }
}

