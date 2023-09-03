package com.stslex93.notes.core.notes.di

import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.core.notes.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NoteModule {

    @Binds
    @Singleton
    fun binds(impl: NoteRepositoryImpl): NoteRepository
}