package com.stslex93.notes.feature.edit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.core.ui.base.ViewModelFactory
import com.stslex93.notes.core.ui.di.ViewModelKey
import com.stslex93.notes.feature.edit.domain.interactor.NoteEditInteractor
import com.stslex93.notes.feature.edit.domain.interactor.NoteEditInteractorImpl
import com.stslex93.notes.feature.edit.ui.EditNoteViewModel
import com.stslex93.notes.feature.edit.ui.store.EditStore
import com.stslex93.notes.feature.edit.ui.store.EditStoreImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EditModule {

    @Binds
    @EditScope
    fun bindsInteractor(impl: NoteEditInteractorImpl): NoteEditInteractor

    @Binds
    @EditScope
    fun bindsStore(impl: EditStoreImpl): EditStore

    @IntoMap
    @ViewModelKey(EditNoteViewModel::class)
    @Binds
    fun bindsViewModel(impl: EditNoteViewModel): ViewModel

    @Binds
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}