package com.stslex93.notes.di.module

import dagger.Module

@Module(
    includes = [
        RepositoryModule::class,
        ViewModelModule::class,
        DataBaseModule::class,
        MapperModule::class,
        UtilsModule::class,
        InteractorModule::class
    ]
)
class AppModule