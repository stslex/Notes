package com.stslex93.notes.core.navigation.di

import com.stslex93.notes.core.navigation.navigator.NavigatorImpl
import com.stslex93.notes.core.ui.di.Navigator
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigator(impl: NavigatorImpl): Navigator
}