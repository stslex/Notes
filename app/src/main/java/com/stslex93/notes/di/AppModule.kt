package com.stslex93.notes.di

import com.stslex93.notes.domain.interactor.impl.MainScreenInteractorImpl
import com.stslex93.notes.domain.interactor.impl.NoteEditInteractorImpl
import com.stslex93.notes.domain.interactor.interf.MainScreenInteractor
import com.stslex93.notes.domain.interactor.interf.NoteEditInteractor
import com.stslex93.notes.ui.edit.EditNoteViewModel
import com.stslex93.notes.ui.edit.store.EditStore
import com.stslex93.notes.ui.edit.store.EditStoreImpl
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
    viewModelOf(::EditNoteViewModel)

    factoryOf(::NoteEditInteractorImpl) { bind<NoteEditInteractor>() }
    factoryOf(::EditStoreImpl) { bind<EditStore>() }

    factoryOf(::MainScreenInteractorImpl) { bind<MainScreenInteractor>() }

    factoryOf(::TimeUtilImpl) { bind<TimeUtil>() }
    factoryOf(::SnackBarUtilImpl) { bind<SnackBarUtil>() }
    factoryOf(SelectorNoteItemsUtil::Base) { bind<SelectorNoteItemsUtil>() }
}