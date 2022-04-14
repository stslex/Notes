package com.stslex.notes.di.module

import com.stslex.notes.data.repository.NoteRepositoryImpl
import com.stslex.notes.data.repository.SearchNoteRepository
import com.stslex.notes.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsRepository(repository: NoteRepositoryImpl): NoteRepository

    @Binds
    @Singleton
    fun bindsSearchNoteRepository(repository: SearchNoteRepository.Base): SearchNoteRepository
}