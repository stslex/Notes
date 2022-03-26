package com.stslex.notes.di.module

import androidx.lifecycle.ViewModelProvider
import com.stslex.notes.ui.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}