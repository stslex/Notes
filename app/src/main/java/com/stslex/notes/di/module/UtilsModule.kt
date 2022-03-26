package com.stslex.notes.di.module

import com.stslex.notes.ui.main.utils.OnNoteClickListener
import com.stslex.notes.ui.main.utils.OnNoteLongClickListener
import com.stslex.notes.ui.utils.snackbar.SnackBarUtil
import com.stslex.notes.ui.utils.snackbar.SnackBarUtilImpl
import com.stslex.notes.ui.utils.time.TimeUtil
import com.stslex.notes.ui.utils.time.TimeUtilImpl
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