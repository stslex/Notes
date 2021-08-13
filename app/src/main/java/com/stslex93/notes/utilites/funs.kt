package com.stslex93.notes.utilites

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.stslex93.notes.NoteApplication
import com.stslex93.notes.R
import com.stslex93.notes.di.component.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is NoteApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

fun Activity.initResources() {
    Resources.apply {
        label_ok = getString(R.string.label_ok)
        label_cancel = getString(R.string.label_cancel)
        label_successful_canceled = getString(R.string.label_successful_canceled)
        label_successful_deleted = getString(R.string.label_successful_deleted)
    }
}

fun FragmentActivity.hideKeyBoard() {
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}

inline fun View.snackBarDelete(crossinline function: () -> Unit) {
    Snackbar.make(this, Resources.label_successful_deleted, Snackbar.LENGTH_SHORT).apply {
        anchorView = this@snackBarDelete
        setAction(Resources.label_cancel) {
            function()
            this@snackBarDelete.actionCancel()
        }
        show()
    }
}

fun View.actionCancel() {
    Snackbar.make(this, Resources.label_successful_canceled, Snackbar.LENGTH_SHORT).apply {
        anchorView = this.view
        setAction(Resources.label_ok) {}
        show()
    }
}