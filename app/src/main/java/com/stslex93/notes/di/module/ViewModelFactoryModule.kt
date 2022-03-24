package com.stslex93.notes.di.module

import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.ui.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}