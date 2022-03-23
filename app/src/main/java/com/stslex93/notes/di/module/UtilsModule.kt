package com.stslex93.notes.di.module

import com.stslex93.notes.ui.main.OnNoteClickListener
import com.stslex93.notes.ui.main.OnNoteLongClickListener
import com.stslex93.notes.ui.utils.interf.SnackBarUtil
import com.stslex93.notes.ui.utils.interf.TimeUtil
import com.stslex93.notes.ui.utils.real.SnackBarUtilImpl
import com.stslex93.notes.ui.utils.real.TimeUtilImpl
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {

    @Binds
    fun bindsClickListener(clicker: OnNoteClickListener.Base): OnNoteClickListener

    @Binds
    fun bindsNoteLongClickListener(clicker: OnNoteLongClickListener.Base): OnNoteLongClickListener

    @Binds
    fun bindsTimeUtil(util: TimeUtilImpl): TimeUtil

    @Binds
    fun bindsSnackBarUtil(util: SnackBarUtilImpl): SnackBarUtil
}