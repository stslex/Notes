package com.stslex93.notes.di.module

import com.stslex93.notes.data.repository.NoteRepositoryImpl
import com.stslex93.notes.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsRepository(repository: NoteRepositoryImpl): NoteRepository
}