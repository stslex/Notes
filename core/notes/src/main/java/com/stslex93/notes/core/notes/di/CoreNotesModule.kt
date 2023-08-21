package com.stslex93.notes.core.notes.di

import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.core.notes.repository.NoteRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreNotesModule = module {
    singleOf(::NoteRepositoryImpl) { bind<NoteRepository>() }
}