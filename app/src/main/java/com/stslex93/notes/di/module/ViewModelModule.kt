package com.stslex93.notes.di.module

import androidx.lifecycle.ViewModel
import com.stslex93.notes.di.key.ViewModelKey
import com.stslex93.notes.ui.NoteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(NoteViewModel::class)
    fun bindsUserViewModel(viewModel: NoteViewModel): ViewModel
}