package com.stslex93.notes.di.module

import com.stslex93.notes.ui.main.utils.SelectorNoteItemsUtil
import com.stslex93.notes.ui.utils.snackbar.SnackBarUtil
import com.stslex93.notes.ui.utils.snackbar.SnackBarUtilImpl
import com.stslex93.notes.ui.utils.time.TimeUtil
import com.stslex93.notes.ui.utils.time.TimeUtilImpl
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {

    @Binds
    fun bindsTimeUtil(util: TimeUtilImpl): TimeUtil

    @Binds
    fun bindsSnackBarUtil(util: SnackBarUtilImpl): SnackBarUtil

    @Binds
    fun bindsSelectorNoteItems(util: SelectorNoteItemsUtil.Base): SelectorNoteItemsUtil
}