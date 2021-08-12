package com.stslex93.notes.di.module

import com.stslex93.notes.data.source.NoteDataSource
import com.stslex93.notes.data.source.NoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {
    @Binds
    fun bindNoteDataSource(source: NoteDataSourceImpl): NoteDataSource
}