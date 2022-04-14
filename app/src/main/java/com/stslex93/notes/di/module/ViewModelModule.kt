package com.stslex93.notes.di.module

import androidx.lifecycle.ViewModel
import com.stslex93.notes.di.key.ViewModelKey
import com.stslex93.notes.ui.edit.EditNoteViewModel
import com.stslex93.notes.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(EditNoteViewModel::class)
    fun bindsNoteViewModel(viewModel: EditNoteViewModel): ViewModel
}