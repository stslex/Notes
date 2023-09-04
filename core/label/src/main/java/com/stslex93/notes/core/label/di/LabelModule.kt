package com.stslex93.notes.core.label.di

import com.stslex93.notes.core.label.repository.LabelRepository
import com.stslex93.notes.core.label.repository.LabelRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface LabelModule {

    @Binds
    @Singleton
    fun bindsLabelRepository(impl: LabelRepositoryImpl): LabelRepository
}