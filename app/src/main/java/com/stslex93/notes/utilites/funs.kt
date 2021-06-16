package com.stslex93.notes.utilites

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.hideKeyBoard() {
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}