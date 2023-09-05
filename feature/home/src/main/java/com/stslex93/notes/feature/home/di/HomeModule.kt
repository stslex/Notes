package com.stslex93.notes.feature.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.core.ui.base.ViewModelFactory
import com.stslex93.notes.core.ui.di.ViewModelKey
import com.stslex93.notes.feature.home.domain.interactor.HomeInteractor
import com.stslex93.notes.feature.home.domain.interactor.HomeInteractorImpl
import com.stslex93.notes.feature.home.navigation.HomeRouter
import com.stslex93.notes.feature.home.navigation.HomeRouterImpl
import com.stslex93.notes.feature.home.ui.HomeViewModel
import com.stslex93.notes.feature.home.ui.store.HomeStore
import com.stslex93.notes.feature.home.ui.store.HomeStoreImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @Binds
    fun bindsViewModel(impl: HomeViewModel): ViewModel

    @Binds
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @HomeScope
    fun bindsInteractor(impl: HomeInteractorImpl): HomeInteractor

    @Binds
    @HomeScope
    fun bindsStore(impl: HomeStoreImpl): HomeStore

    @Binds
    @HomeScope
    fun bindRouter(impl: HomeRouterImpl): HomeRouter
}