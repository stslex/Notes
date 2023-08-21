package com.stslex93.notes.di

import com.stslex93.notes.domain.interactor.MainScreenInteractor
import com.stslex93.notes.domain.interactor.MainScreenInteractorImpl
import com.stslex93.notes.ui.main.MainViewModel
import com.stslex93.notes.ui.main.utils.SelectorNoteItemsUtil
import com.stslex93.notes.ui.utils.snackbar.SnackBarUtil
import com.stslex93.notes.ui.utils.snackbar.SnackBarUtilImpl
import com.stslex93.notes.ui.utils.time.TimeUtil
import com.stslex93.notes.ui.utils.time.TimeUtilImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
    factoryOf(::MainScreenInteractorImpl) { bind<MainScreenInteractor>() }
    factoryOf(::TimeUtilImpl) { bind<TimeUtil>() }
    factoryOf(::SnackBarUtilImpl) { bind<SnackBarUtil>() }
    factoryOf(SelectorNoteItemsUtil::Base) { bind<SelectorNoteItemsUtil>() }
}