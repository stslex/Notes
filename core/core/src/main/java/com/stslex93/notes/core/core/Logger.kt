package com.stslex.aproselection.core.core

import android.util.Log

object Logger {

    private const val DEFAULT_TAG = "NOTES"

    fun exception(
        throwable: Throwable,
        tag: String? = null,
        message: String? = null
    ) {
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.e(
            currentTag,
            message ?: throwable.message.orEmpty(),
            throwable,
        )
    }

    fun debug(
        message: String,
        tag: String? = null,
    ) {
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.d(currentTag, message)
    }
}