package com.stslex93.notes.di.component

import com.stslex93.notes.di.module.*
import com.stslex93.notes.utilites.BaseFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)
}