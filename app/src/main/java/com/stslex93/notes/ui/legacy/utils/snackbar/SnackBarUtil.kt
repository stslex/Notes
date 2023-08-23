package com.stslex93.notes.ui.legacy.utils.snackbar

import android.view.View

interface SnackBarUtil {

    fun showErrorMessage(view: View, message: String)

    fun showSuccess(view: View, action: () -> Unit)
}