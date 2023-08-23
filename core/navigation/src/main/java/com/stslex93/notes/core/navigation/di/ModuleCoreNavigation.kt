package com.stslex93.notes.core.navigation.di

import androidx.navigation.NavHostController
import org.koin.core.module.Module
import org.koin.dsl.module
import com.stslex93.notes.core.navigation.navigator.Navigator
import com.stslex93.notes.core.navigation.navigator.NavigatorImpl

val moduleCoreNavigation: (navHostController: NavHostController) -> Module = { navHostController ->
    module {
        single<Navigator> {
            NavigatorImpl(navHostController)
        }
    }
}