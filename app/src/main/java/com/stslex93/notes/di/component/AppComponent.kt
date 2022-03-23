package com.stslex93.notes.di.component

import android.content.Context
import com.stslex93.notes.di.module.AppModule
import com.stslex93.notes.ui.edit.EditFragment
import com.stslex93.notes.ui.main.MainFragment
import com.stslex93.notes.ui.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder
        fun create(): AppComponent
    }

    fun inject(fragment: EditFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: MainFragment)
}