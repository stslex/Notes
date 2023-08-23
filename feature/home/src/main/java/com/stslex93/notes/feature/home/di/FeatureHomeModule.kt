package com.stslex93.notes.feature.home.di

import com.stslex93.notes.feature.home.domain.interactor.HomeInteractor
import com.stslex93.notes.feature.home.domain.interactor.HomeInteractorImpl
import com.stslex93.notes.feature.home.ui.HomeViewModel
import com.stslex93.notes.feature.home.ui.store.HomeStore
import com.stslex93.notes.feature.home.ui.store.HomeStoreImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val featureHomeModule = module {
    factoryOf(::HomeInteractorImpl) { bind<HomeInteractor>() }
    viewModelOf(::HomeViewModel)
    factoryOf(::HomeStoreImpl) { bind<HomeStore>() }
}