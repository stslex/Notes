package com.stslex93.notes.core.ui.base

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf

abstract class NavigationScreen(
    open val route: String,
    open val arguments: Bundle? = null
) {
    constructor(route: String, extra: Extra) : this(route, extra.saveBundle)

    data class Extra(val key: String, val parcelable: Parcelable)

    open val isSingleTop: Boolean = false
}

val NavigationScreen.Extra?.saveBundle: Bundle
    get() = bundleOf().apply {
        this@saveBundle?.let { extra ->
            putParcelable(extra.key, extra.parcelable)
        }
    }