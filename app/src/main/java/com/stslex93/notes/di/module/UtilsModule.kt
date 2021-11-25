package com.stslex93.notes.di.module

import com.stslex93.notes.ui.main.OnNoteClickListener
import com.stslex93.notes.ui.main.OnNoteLongClickListener
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {

    @Binds
    fun bindsClickListener(clicker: OnNoteClickListener.Base): OnNoteClickListener

    @Binds
    fun bindsNoteLongClickListener(clicker: OnNoteLongClickListener.Base): OnNoteLongClickListener
}