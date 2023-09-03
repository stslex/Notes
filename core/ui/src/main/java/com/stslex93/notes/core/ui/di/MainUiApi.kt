package com.stslex93.notes.core.ui.di

import android.content.Context

interface MainUiApi {

    val navigationApi: NavigationApi
}

val Context.navigationApi: NavigationApi
    get() = (this as MainUiApi).navigationApi
