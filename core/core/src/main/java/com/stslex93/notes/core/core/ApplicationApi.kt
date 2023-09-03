package com.stslex93.notes.core.core

import android.content.Context

interface ApplicationApi {

    val appApi: AppApi
}

val Context.appApi: AppApi
    get() = (this.applicationContext as ApplicationApi).appApi