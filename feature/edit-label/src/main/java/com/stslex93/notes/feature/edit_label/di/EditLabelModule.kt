package com.stslex93.notes.feature.edit_label.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.core.ui.base.ViewModelFactory
import com.stslex93.notes.core.ui.di.ViewModelKey
import com.stslex93.notes.feature.edit_label.domain.interactor.EditLabelInteractor
import com.stslex93.notes.feature.edit_label.domain.interactor.EditLabelInteractorImpl
import com.stslex93.notes.feature.edit_label.navigation.router.EditLabelRouter
import com.stslex93.notes.feature.edit_label.navigation.router.EditLabelRouterImpl
import com.stslex93.notes.feature.edit_label.ui.EditLabelViewModel
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStoreImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EditLabelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditLabelViewModel::class)
    fun bindsViewModel(impl: EditLabelViewModel): ViewModel

    @Binds
    @EditLabelScope
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @EditLabelScope
    fun bindsInteractor(impl: EditLabelInteractorImpl): EditLabelInteractor

    @Binds
    @EditLabelScope
    fun bindsStore(impl: EditLabelStoreImpl): EditLabelStore

    @Binds
    @EditLabelScope
    fun bindsRouter(impl: EditLabelRouterImpl): EditLabelRouter
}