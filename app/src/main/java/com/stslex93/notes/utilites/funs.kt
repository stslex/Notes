package com.stslex93.notes.utilites

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

fun FragmentActivity.hideKeyBoard() {
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}

inline fun View.snackBarDelete(crossinline function: () -> Unit) {
    Snackbar.make(this, "Successful deleted", Snackbar.LENGTH_SHORT).apply {
        anchorView = this@snackBarDelete
        setAction("cancel") {
            function()
            this@snackBarDelete.actionCancel()
        }
        show()
    }
}

fun View.actionCancel() {
    Snackbar.make(this, "It's Canceled", Snackbar.LENGTH_SHORT).apply {
        anchorView = this.view
        setAction("OK") {}
        show()
    }
}